#include <stdlib.h>
#include <ctime>

#include "physfs.h"
#include "NWNXPhysfs.h"

extern CNWNXPhysfs physfs;

int HandleResourceExistsEvent(WPARAM p, LPARAM a) {
    ResManResExistsStruct *exists = reinterpret_cast<ResManResExistsStruct*>(p);
    if(PHYSFS_exists(exists->resRefWithExt)) {
        exists->exists = true;
        return 1;
    }
    return 0;
}

int HandleDemandResourceEvent(WPARAM p, LPARAM a) {
    ResManDemandResStruct *event = reinterpret_cast<ResManDemandResStruct*>(p);
    if(event->restype == 0x07DA && event->mtime != 0 && PHYSFS_exists(event->resref)) {
        return 1;
    }

    PHYSFS_File* file = PHYSFS_openRead(event->resref);
    if (!file) { return 0; }

    PHYSFS_sint64 size = PHYSFS_fileLength(file);
    if (size == -1) { return 0; }
    unsigned char *buffer = new unsigned char[size];

    PHYSFS_sint64 res = PHYSFS_read(file, buffer, size, 1);
    if( res == -1 ) {
        physfs.Log(0, "Failed reading: %s -\n%s\n", event->resref, PHYSFS_getLastError());
        delete[] buffer;
        PHYSFS_close(file);
        return 0;
    }

    physfs.Log(4, "Read: %s : %d bytes\n", event->resref, size);

    event->mtime = std::time(0);
    event->pData = buffer;
    event->size = size;
    PHYSFS_close(file);

    return 1;
}

int HandlePluginsLoaded(WPARAM p, LPARAM a) {
    HANDLE handleResourceExists = HookEvent("NWNX/ResMan/ResourceExists", HandleResourceExistsEvent);
    if (!handleResourceExists) {
        physfs.Log(0, "Cannot hook NWNX/ResMan/ResourceExists!\n");
    }

    HANDLE handleDemandResource = HookEvent("NWNX/ResMan/DemandResource", HandleDemandResourceEvent);
    if (!handleDemandResource) {
        physfs.Log(0, "Cannot hook NWNX/ResMan/DemandResource!\n");
    }

    return 0;
}

CNWNXPhysfs::CNWNXPhysfs() {
    confKey = strdup("PHYSFS");
}

bool CNWNXPhysfs::OnCreate(gline *nwnxConfig, const char *LogDir) {
    char log[128];
    sprintf(log, "%s/nwnx_physfs.txt", LogDir);
    if (!CNWNXBase::OnCreate(nwnxConfig, log))
        return false;

    // write copy information to the log file
    Log (0, "NWNX Physfs version 0.1 for Linux.\n");
    Log (0, "(c) 2014 by jmd (jmd2028 at gmail dot com) aka leo_x\n");

    HANDLE handlePluginsLoaded = HookEvent("NWNX/Core/PluginsLoaded", HandlePluginsLoaded);
    if (!handlePluginsLoaded) {
        Log(0, "Cannot hook plugins loaded event!\n");
        return false;
    }

    char fake_argv0[500];
    char *ptr = realpath("./nwserver", fake_argv0);
    if (!ptr) { return false; }
    Log(3, "Fake argv0: %s\n", ptr);

    if (!PHYSFS_init(ptr)) {
        Log(0, "Failed to initialize PhysFS!\n");
    }

    if (nwnxConfig->exists(confKey)) {
        int i = 0;
        while(true) {
            sprintf(log, "load%d", i++);
            std::string file = (*nwnxConfig)[confKey][log];
            if (file.size() > 0) {
                Log(0, "Adding %s to search path.\n", file.c_str());
                int res = PHYSFS_mount(file.c_str(), NULL, 1);
                if (res == 0) {
                    Log(0, "Failed to add archive: %s\n", PHYSFS_getLastError());
                }
            }
            else {
                break;
            }
        }
    }

    return true;
}

CNWNXPhysfs::~CNWNXPhysfs() {
    PHYSFS_deinit();
}

char *CNWNXPhysfs::OnRequest (char *gameObject, char *Request, char *Parameters) {
    Log(1, "StrReq: \"%s\"\nParams: \"%s\"\n", Request, Parameters);
    Log(1, "\nReturn: \"%s\"\n", Parameters);
    return NULL;
}

unsigned long CNWNXPhysfs::OnRequestObject (char *gameObject, char *Request) {
    unsigned long ret = OBJECT_INVALID;

    Log(1, "ObjReq: \"%s\"\n", Request);
    Log(1, "Return: %08X\n", ret);

    return ret;
}
