package org.crownpeak.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.crownpeak.api.request.*;
import org.crownpeak.api.response.*;

import junit.framework.TestCase;

public class APITest extends TestCase {

	public static API Authenticate() {
		System.setProperty("DproxySet", "true");
		System.setProperty("DproxyHost", "127.0.0.1");
		System.setProperty("DproxyPort", "8888");
		System.setProperty("Djavax.net.ssl.trustStore", "C:\\Program Files\\Java\\jdk1.8.0_211\\bin\\FiddlerKeyStore");
		System.setProperty("Djavax.net.ssl.trustStorePassword", "FiddlerWorld#1993");
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
		} catch( Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void AuthenticateTest() {
		API api = Authenticate();
		assertEquals((boolean) true, (boolean) api.isAuthenticated());
	}
	
	public void AttachTest() throws Exception{
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("AttachTest", asset, false);
		BufferedImage image = ImageIO.read(new File("D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\test-folder\\gravity.png"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", outputStream);
		AssetAttachRequest request = new AssetAttachRequest(createResponse.asset.id,outputStream.toByteArray(),"test");
		AssetAttachResponse response = asset.Attach(request);
		asset.delete(createResponse.asset.id);
		if(!response.isSuccessful()) {
			fail("Attach failed");
		}	
	}
	
	public void BranchTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("BranchTest", asset, false);
		AssetBranchResponse branchResponse = asset.branch(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		asset.delete(branchResponse.asset.id);
		if(branchResponse.asset.branchId != createResponse.asset.id) {
			fail("Branch Id doesn't match asset ID");
		}
	}

	

	public void CreateTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("CreateAsset", asset, false);
		asset.delete(createResponse.asset.id);
		createResponse = APITestHelpers.createTemps("CreateAsset", asset, true);
		asset.delete(createResponse.asset.id);
	}

	
	public void DeleteTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("DeleteAsset", asset, true);
		asset.delete(createResponse.asset.id);
		APITestHelpers.TestNotExists(createResponse.asset.fullPath, asset);
	}
	
	public void ExistsTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		APITestHelpers.TestExists("/David Test/", asset);
		APITestHelpers.TestExists(17663, asset);

	}
	
	public void MoveTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("AttachTest", asset, false);
		AssetCreateResponse folderResponse = APITestHelpers.createFolder("AttachFolder",asset);
		AssetMoveResponse moveResponse = asset.move(new AssetMoveRequest(createResponse.asset.id,folderResponse.asset.id));
		AssetExistsResponse existsResponse = asset.exists("/David Test/AttachFolder/AttachTest");
		asset.delete(createResponse.asset.id);
		asset.delete(folderResponse.asset.id);
		
		if(existsResponse.isSuccessful() && !existsResponse.exists) {
			fail("Asset moved from failure");
		}
		
		if(!moveResponse.isSuccessful()) {
			fail("Move failed");
		}
		
		
	}

	public void ReadTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("FieldsReadAsset", asset, true);
		AssetReadResponse response = asset.read(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		String fieldName = response.fields[0].name;
		if (response.fields.length == 0 || !fieldName.contentEquals("body")) {
			fail("Body was not correct");
		}
	}

	public void UpdateTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("FieldsUpdateAsset", asset, true);
		HashMap<String, String> fields = new HashMap<String, String>();
		fields.put("test", "test");
		fields.put("test2","test2");
		AssetUpdateRequest request = new AssetUpdateRequest(createResponse.asset.id, fields);
		@SuppressWarnings("unused")
		AssetUpdateResponse updateResponse = asset.update(request);
		AssetReadResponse response = asset.read(createResponse.asset.id);
		if(response.fields.length < 2) {
			asset.delete(createResponse.asset.id);
			fail("New values not added");
		}
		for(int i=0; i< response.fields.length;i++) {
			if(!fields.containsKey(response.fields[i].name)) {
				asset.delete(createResponse.asset.id);
				fail("One of the fields does not exist: " + response.fields[i].name);
			}
		}
		
		request.fields = null;
		request.fieldsToDelete = new ArrayList<String>();
		request.fieldsToDelete.add("test");
		request.fieldsToDelete.add("test2");
		
		updateResponse = asset.update(request);
		response = asset.read(createResponse.asset.id);
		for(int i=0; i< response.fields.length;i++) {
			if(fields.containsKey(response.fields[i].name)) {
				asset.delete(createResponse.asset.id);
				fail("One of the deleted fields exist: " + response.fields[i].name);
			}
		}
		asset.delete(createResponse.asset.id);
		
		
	}
}

class APITestHelpers extends TestCase {
	public static void TestNotExists(String path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response, false);
	}

	public static void TestNotExists(int path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response, false);
	}

	public static void TestExists(String path, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(path);
		assertExists(response, true);
	}

	public static void TestExists(int id, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(id);
		assertExists(response, true);
	}

	public static void assertExists(AssetExistsResponse response, Boolean test) {
		if (response.exists == null) {
			fail("Exists is Null");
		}
		assertEquals((boolean) test, (boolean) response.exists);
	}

	public static AssetCreateResponse createFolder(String name, AccessAsset asset) {
		AssetCreateRequest request = new AssetCreateRequest();
		request.createFolder(name, 17663);
		AssetCreateResponse response = asset.create(request);
		if (response.asset == null) {
			fail("Folder was not created successfully" + response.errorMessage);
		}
		APITestHelpers.TestExists("/David Test/" + name, asset);
		return response;
	}
	
	public static AssetCreateResponse createTemps(String name, AccessAsset asset, Boolean developerTemplate) {
		AssetCreateRequest request;
		if (!developerTemplate) {
			request = new AssetCreateRequest(name, 17663, 801);
		} else {
			request = new AssetCreateRequest();
			request.createDevAsset(name, 17663);
		}

		AssetCreateResponse response = asset.create(request);
		if (response.asset == null) {
			fail("Asset was not created successfully" + response.errorMessage);
		}
		APITestHelpers.TestExists("/David Test/" + name, asset);
		return response;
	}

}
