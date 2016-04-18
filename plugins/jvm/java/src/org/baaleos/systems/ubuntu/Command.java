package org.baaleos.systems.ubuntu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Command {

	public static String exec(String command) throws IOException, InterruptedException{
		Process cmdProc = Runtime.getRuntime().exec(command);
		StringBuilder sb = new StringBuilder();
		cmdProc.waitFor();

	    BufferedReader reader = 
	         new BufferedReader(new InputStreamReader(cmdProc.getInputStream()));

	    String line = "";			
	    while ((line = reader.readLine())!= null) {
	    	sb.append(line + "\n");
	    }
		return sb.toString();
	}
}
