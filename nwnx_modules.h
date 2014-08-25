#ifndef NWNX_MODULES_H_
#define NWNX_MODULES_H_

typedef struct
{
	const char* database;
	const char* key;
	char* player;
	unsigned char *pData;
	int size;
}
	SCORCOStruct;

typedef struct
{
    const char* resref; // "whatever.uti"
    unsigned int restype;
    unsigned char *pData;
    int size;
    time_t mtime;
    const time_t minimum_mtime;
}
    ResManDemandResStruct;

typedef struct
{
    const char* resRefWithExt;
    time_t mtime;
    bool exists;
}
    ResManResExistsStruct;

#endif  // NWNX_MODULES_H_
