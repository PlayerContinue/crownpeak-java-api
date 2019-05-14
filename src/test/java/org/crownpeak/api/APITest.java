package org.crownpeak.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetExistsResponse;

import junit.framework.TestCase;

public class APITest extends TestCase {

	public static API Authenticate() {
		Scanner scan;
		try {
			scan = new Scanner(
					new File("D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\login-info.txt"));
			scan.useDelimiter(",");

			API api = new API(scan.next(), scan.next(), scan.next());
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
		assertEquals((boolean) true, (boolean) api.isAuthenticated());
	}

	public void ExistsTest() {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		APITestHelpers.TestExists("/David Test/", asset);
		APITestHelpers.TestExists(17663, asset);

	}


	public void CreateTest() {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateRequest request = new AssetCreateRequest("test",17663,801);
		//request.createDevAsset("test123", 17663);
		APITestHelpers.TestExists(17663, asset);
		AssetCreateResponse response = asset.create(request);
		if(response.asset==null) {
			fail("Asset was not created successfully" + response.errorMessage);
		}
		APITestHelpers.TestExists("/David Test/test123", asset);
	}
}

class APITestHelpers  extends TestCase{
	public static void TestNotExists(String path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response,false);
	}
	
	public static void TestNotExists(int path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response,false);
	}
	public static void TestExists(String path, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(path);
		assertExists(response,true);
	}

	public static void TestExists(int id, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(id);
		assertExists(response,true);
	}
	
	public static void assertExists(AssetExistsResponse response, Boolean test) {
		if (response.exists == null) {
			fail("Exists is Null");
		}
		assertEquals((boolean)test, (boolean) response.exists);
	}
	
	
}
