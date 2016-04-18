package org.baaleos.systems.ubuntu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.util.Scanner;

public class Command {

	public static String exec(String command, String strQuery) throws IOException, InterruptedException{
		ProcessBuilder builder = new ProcessBuilder("/usr/bin/"+command);
        String out = "";
        Process process = builder.start();

        OutputStream stdin = process.getOutputStream(); // <- Eh?
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));

        writer.write(strQuery);
        writer.flush();
        writer.close();

        Scanner scanner = new Scanner(stdout);
        while (scanner.hasNextLine()) {
        	out += scanner.nextLine();
        }
		return out;
	}
}
