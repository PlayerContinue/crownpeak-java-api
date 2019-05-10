package org.crownpeak.api.crownpeak_java_api;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.crownpeak.api.API;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scan;
		try {
			scan = new Scanner(new File("D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\login-info.txt"));
			scan.useDelimiter(",");
	    	
	        API api = new API(scan.next(),scan.next(),scan.next());
	        api.login(scan.next(), scan.next());
	        scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
