package org.crownpeak.api;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.crownpeak.api.response.ExistsResponse;

import junit.framework.TestCase;
public class APITest extends TestCase{
	
	public static API Authenticate() {
		Scanner scan;
		try {
			scan = new Scanner(new File("D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\login-info.txt"));
			scan.useDelimiter(",");
	    	
	        API api = new API(scan.next(),scan.next(),scan.next());
	        api.login(scan.next(), scan.next());
	        scan.close();
	        
	        return api;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void AuthenticateTest() {
		API api = Authenticate();
		assertEquals((boolean)true,(boolean)api.isAuthenticated());
	}
	
	public void ExistsTest() {
		API api = Authenticate();
		TestExists("/David Test/test",api);
		
	}
	
	public void TestExists(String path, API api) {
		AccessAsset asset = new AccessAsset(api);
		ExistsResponse response = asset.Exists(path);
		assertEquals(true,response.exists);
	}
}
