package org.crownpeak.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.imageio.ImageIO;

import org.crownpeak.api.request.AssetAttachRequest;
import org.crownpeak.api.request.AssetCreateRequest;
import org.crownpeak.api.request.AssetExecuteWorkflowCommandRequest;
import org.crownpeak.api.request.AssetMoveRequest;
import org.crownpeak.api.request.AssetPagedRequest;
import org.crownpeak.api.request.AssetPublishRefreshRequest;
import org.crownpeak.api.request.AssetPublishRequest;
import org.crownpeak.api.request.AssetUpdateRequest;
import org.crownpeak.api.request.AssetUploadRequest;
import org.crownpeak.api.request.OrderType;
import org.crownpeak.api.request.VisibilityType;
import org.crownpeak.api.response.AssetAttachResponse;
import org.crownpeak.api.response.AssetBranchResponse;
import org.crownpeak.api.response.AssetCreateResponse;
import org.crownpeak.api.response.AssetExecuteWorkflowCommandResponse;
import org.crownpeak.api.response.AssetExistsResponse;
import org.crownpeak.api.response.AssetFieldsResponse;
import org.crownpeak.api.response.AssetLogResponse;
import org.crownpeak.api.response.AssetMoveResponse;
import org.crownpeak.api.response.AssetPagedResponse;
import org.crownpeak.api.response.AssetPublishRefreshResponse;
import org.crownpeak.api.response.AssetPublishResponse;
import org.crownpeak.api.response.AssetReadResponse;
import org.crownpeak.api.response.AssetRenameResponse;
import org.crownpeak.api.response.AssetRouteResponse;
import org.crownpeak.api.response.AssetUndeleteResponse;
import org.crownpeak.api.response.AssetUpdateResponse;
import org.crownpeak.api.response.AssetUploadResponse;
import org.crownpeak.api.response.WorkflowReadResponse;
import org.crownpeak.api.response.WorkflowsReadResponse;

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
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void AuthenticateTest() {
		API api = Authenticate();
		assertEquals((boolean) true, (boolean) api.isAuthenticated());
	}

	public void AttachTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("AttachTest", asset, false);
		BufferedImage image = ImageIO.read(new File(
				"D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\test-folder\\gravity.png"));
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", outputStream);
		AssetAttachRequest request = new AssetAttachRequest(createResponse.asset.id, outputStream.toByteArray(),
				"test");
		AssetAttachResponse response = asset.attach(request);
		asset.delete(createResponse.asset.id);
		if (!response.isSuccessful()) {
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
		if (branchResponse.asset.branchId != createResponse.asset.id) {
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
	
	public void ExecuteWorkflowCommandTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AccessWorkflow workflow = new AccessWorkflow(api);
		WorkflowsReadResponse workflowresponse = workflow.read();
		AssetCreateResponse createResponse = APITestHelpers.createTemps("AttachTest", asset, false);
		int workflowId = createResponse.asset.workflow_id;
		if(workflowId != 15) {
			asset.delete(createResponse.asset.id);
			fail("Asset has wrong workflow");
		}
		
		AssetExecuteWorkflowCommandRequest request = new AssetExecuteWorkflowCommandRequest(createResponse.asset.id,57,false);
		AssetExecuteWorkflowCommandResponse response = asset.executeWorkflowCommand(request);
		AssetReadResponse readResponse = asset.read(createResponse.asset.id);
		
		asset.delete(createResponse.asset.id);
		
		if(readResponse.asset.status != 783) {
			fail("Asset was not moved through workflow " + readResponse.asset.status);
		}
		
		
	}

	public void ExistsTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		APITestHelpers.TestExists("/David Test/", asset);
		APITestHelpers.TestExists(17663, asset);

	}

	public void FieldTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("FieldsReadAsset", asset, true);
		AssetFieldsResponse response = asset.fields(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		String fieldName = response.fields[0].name;
		if (response.fields.length == 0 || !fieldName.contentEquals("body")) {
			fail("Body was not correct");
		}
	}
	
	
	public void MoveTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("AttachTest", asset, false);
		AssetCreateResponse folderResponse = APITestHelpers.createFolder("AttachFolder", asset);
		AssetMoveResponse moveResponse = asset
				.move(new AssetMoveRequest(createResponse.asset.id, folderResponse.asset.id));
		AssetExistsResponse existsResponse = asset.exists("/David Test/AttachFolder/AttachTest");
		asset.delete(createResponse.asset.id);
		asset.delete(folderResponse.asset.id);

		if (existsResponse.isSuccessful() && !existsResponse.exists) {
			fail("Asset moved from failure");
		}

		if (!moveResponse.isSuccessful()) {
			fail("Move failed");
		}

	}
	
	public void PagedTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("PagedAsset1", asset, true);
		AssetCreateResponse createResponse2 = APITestHelpers.createTemps("PagedAsset2", asset, true);
		AssetCreateResponse createResponse3 = APITestHelpers.createTemps("PagedAsset3", asset, true);
		
		AssetPagedResponse pagedResponse = asset.paged(new AssetPagedRequest(17663, -1, 0, false, false, OrderType.ASCENDING, 10,
				false, "", VisibilityType.NORMAL));
		
		asset.delete(createResponse.asset.id);
		asset.delete(createResponse2.asset.id);
		asset.delete(createResponse3.asset.id);
		
		if(!pagedResponse.isSuccessful() && pagedResponse.assets.length != 3) {
			fail("Asset count did not match");
		}
		
		
	}
	
	

	public void PublishTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("PublishTest", asset, true, 18214);
		AssetPublishResponse publishResponse = asset.publish(new AssetPublishRequest(new int[] { createResponse.asset.id }, false));
		asset.delete(createResponse.asset.id);
		if (!publishResponse.isSuccessful() || publishResponse.publishingSessionId <= 0) {
			fail("Publishing was not successful");
		}
	}
	
	public void PublishRefreshTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("PublishRefreshTest", asset, false);
		AssetPublishRefreshResponse response = asset.publishRefresh(new AssetPublishRefreshRequest(new int[] {17633},783,true));
		asset.delete(createResponse.asset.id);
		if(!response.isSuccessful()) {
			fail("Refresh failed");
		}
	}

	
	public void ReadTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("ReadTestAsset", asset, true);
		AssetReadResponse readResponse = asset.read(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		
		if(!readResponse.asset.label.contentEquals("ReadTestAsset")) {
			fail("Asset was not read successfully");
		}
	}
	
	public void RenameTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("RenameTestAsset", asset, true);
		AssetRenameResponse response = asset.rename(createResponse.asset.id, "renameTestAsset2");
		asset.delete(createResponse.asset.id);
		
		if(!response.asset.label.contentEquals("renameTestAsset2")) {
			fail("Asset not renamed");
		}
		
	}
	
	public void RouteTest() throws Exception{
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("RenameTestAsset", asset, false);
		AssetRouteResponse routeResponse = asset.route(createResponse.asset.id,783);
		AssetReadResponse readResponse = asset.read(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		if(readResponse.asset.status != 783) {
			fail("Route Failed");
		}
	}
	
	
	public void WorkflowReadTest() throws Exception{
		API api = Authenticate();
		AccessWorkflow workflow = new AccessWorkflow(api);
		WorkflowReadResponse response = workflow.read(11);
		if(response.workflow.name == "Basic Workflow") {
			fail("Correct Workflow was not recieved");
		}
	}
	
	public void WorkflowsReadTest() throws Exception{
		API api = Authenticate();
		AccessWorkflow workflow = new AccessWorkflow(api);
		WorkflowsReadResponse response = workflow.read();
		if(response.workflows.get("11").name == "Basic Workflow") {
			fail("Correct Workflow was not recieved");
		}
	}

	public void UpdateTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("FieldsUpdateAsset", asset, true);
		HashMap<String, String> fields = new HashMap<String, String>();
		fields.put("test", "test");
		fields.put("test2", "test2");
		AssetUpdateRequest request = new AssetUpdateRequest(createResponse.asset.id, fields);
		@SuppressWarnings("unused")
		AssetUpdateResponse updateResponse = asset.update(request);
		AssetFieldsResponse response = asset.fields(createResponse.asset.id);
		if (response.fields.length < 2) {
			asset.delete(createResponse.asset.id);
			fail("New values not added");
		}
		for (int i = 0; i < response.fields.length; i++) {
			if (!fields.containsKey(response.fields[i].name)) {
				asset.delete(createResponse.asset.id);
				fail("One of the fields does not exist: " + response.fields[i].name);
			}
		}

		request.fields = null;
		request.fieldsToDelete = new ArrayList<String>();
		request.fieldsToDelete.add("test");
		request.fieldsToDelete.add("test2");

		updateResponse = asset.update(request);
		response = asset.fields(createResponse.asset.id);
		for (int i = 0; i < response.fields.length; i++) {
			if (fields.containsKey(response.fields[i].name)) {
				asset.delete(createResponse.asset.id);
				fail("One of the deleted fields exist: " + response.fields[i].name);
			}
		}
		asset.delete(createResponse.asset.id);

	}
	
	public void UndeleteTest() throws Exception {
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("FieldsUpdateAsset", asset, true);
		asset.delete(createResponse.asset.id);
		AssetUndeleteResponse response = asset.undelete(createResponse.asset.id);
		if(!response.isSuccessful()) {
			fail("Undelete uncessful");
		}
		
		AssetReadResponse readResponse = asset.read(createResponse.asset.id);
		asset.delete(createResponse.asset.id);
		if(readResponse.asset.is_deleted == true) {
			fail("Asset was not undeleted");
		}
	}
	
	public void UploadTest() throws Exception{
		API api = Authenticate();
		AccessAsset asset = new AccessAsset(api);
		Path fileLocation = Paths.get(
				"D:\\Documents\\GitHub\\CP\\Java API\\Maven API\\crownpeak-java-api\\test-folder\\gravity.png");
		byte[] data = Files.readAllBytes(fileLocation);
		AssetUploadResponse uploadResponse = asset.upload(new AssetUploadRequest(data,17663,"UploadTest.png"));
		APITestHelpers.TestExists(uploadResponse.asset.id,asset);
		asset.delete(uploadResponse.asset.id);
		
		
	}
	
	public void LogTest() throws Exception {
		API api = Authenticate();
		AccessUtil util = new AccessUtil(api);
		AssetLogResponse response = util.log("test");
		if(!response.isSuccessful()) {
			fail("Message failed");
		}
		AccessAsset asset = new AccessAsset(api);
		AssetCreateResponse createResponse = APITestHelpers.createTemps("LogAsset", asset, true);
		response = util.log(createResponse.asset.id,"test");
		asset.delete(createResponse.asset.id);
		if(!response.isSuccessful()) {
			fail("Asset Message Fail");
		}
	}
}



class APITestHelpers extends TestCase {
	public static void TestNotExists(String path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response, false, asset);
	}

	public static void TestNotExists(int path, AccessAsset asset) {
		AssetExistsResponse response = asset.exists(path);
		assertExists(response, false, asset);
	}

	public static void TestExists(String path, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(path);
		assertExists(response, true, asset);
	}

	public static void TestExists(int id, AccessAsset asset) {

		AssetExistsResponse response = asset.exists(id);
		assertExists(response, true,asset);
	}

	public static void assertExists(AssetExistsResponse response, Boolean test,AccessAsset asset) {
		if (response.exists == null) {
			asset.delete(response.assetId);
			fail("Exists is Null");
		}
		if(test != response.exists) {
			asset.delete(response.assetId);
			fail(test + " does not equal " + response.exists);
		}
		
	}

	public static AssetCreateResponse createFolder(String name, AccessAsset asset, int createLocation) {
		AssetCreateRequest request = new AssetCreateRequest();
		if (createLocation <= 0) {
			createLocation = 17663;
		}
		request.createFolder(name, createLocation);
		AssetCreateResponse response = asset.create(request);
		if (response.asset == null) {
			fail("Folder was not created successfully" + response.errorMessage);
		}
		if (createLocation == 17663) {
			APITestHelpers.TestExists("/David Test/" + name, asset);
		}
		return response;
	}

	public static AssetCreateResponse createFolder(String name, AccessAsset asset) {
		return createFolder(name, asset, -1);
	}

	public static AssetCreateResponse createTemps(String name, AccessAsset asset, Boolean developerTemplate,
			int createLocation) {
		AssetCreateRequest request;
		if (createLocation < 0) {
			createLocation = 17663;

		}

		if (!developerTemplate) {
			request = new AssetCreateRequest(name, createLocation, 801);
		} else {
			request = new AssetCreateRequest();
			request.createDevAsset(name, createLocation);
		}

		AssetCreateResponse response = asset.create(request);
		if (response.asset == null) {
			fail("Asset was not created successfully" + response.errorMessage);
		}
		if (createLocation == 17663) {
			APITestHelpers.TestExists("/David Test/" + name, asset);
		}
		return response;
	}

	public static AssetCreateResponse createTemps(String name, AccessAsset asset, Boolean developerTemplate) {
		return createTemps(name, asset, developerTemplate, -1);
	}

}
