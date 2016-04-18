package org.baaleos.systems.ubuntu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

	public static String exec(String command) throws IOException{
		Process cmdProc = Runtime.getRuntime().exec(command);
		String str = null;

		BufferedReader stdoutReader = new BufferedReader(
		         new InputStreamReader(cmdProc.getInputStream()));
		String line;
		while ((line = stdoutReader.readLine()) != null) {
		   // process procs standard output here
			str+= line;
		}
		
		BufferedReader stderrReader = new BufferedReader(
		         new InputStreamReader(cmdProc.getErrorStream()));
		while ((line = stderrReader.readLine()) != null) {
		   // process procs standard error here
			str += line;
		}
		return str;
	}
}
