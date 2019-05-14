package org.crownpeak.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.crownpeak.api.request.APIRequest;
import org.crownpeak.api.response.APIResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class MakeRequest {

	/**
	 * make a asynchronous request to the API based on data provided
	 * 
	 * @param url     - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static final <T extends APIResponse> T makeRequest(String urlPath, APIRequest request, APIData apiData, Class<T> responseClass) {

		return MakeRequest.makeRequestSyncV2("https://" + apiData.host + "/" + apiData.instance + apiData.webAPIRoot
				+ MakeRequest.modifyURLPath(urlPath), request, apiData, responseClass);
	}

	/**
	 * make a synchronous request to the API based on data provided
	 * 
	 * @param url     - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static final <T extends APIResponse> T makeRequestSync(String urlPath, APIRequest request, APIData apiData,
			Class<T> responseClass) {
		return MakeRequest.makeRequestSyncV2("https://" + apiData.host + "/" + apiData.instance + "/"
				+ apiData.webAPIRoot + MakeRequest.modifyURLPath(urlPath), request, apiData, responseClass);
	}

	/**
	 * make a synchronous request to the API based on data provided
	 * 
	 * @param url     - The full url to make the call to
	 * @param request - Object containing the parameters of the request to make
	 * @param apiData - The data of the current api
	 * @throws IOException
	 */
	public static final <T extends APIResponse> T makeRequestSyncV2(String url, APIRequest request, APIData apiData,
			Class<T> responseClass) {

		CloseableHttpResponse  response = null;
		try {
			int failureCount = 0;
			T returnValue = null;
			int timeoutWait;
			String retryAfter = null;
			do {
			
			if(response!=null && response.getStatusLine().getStatusCode() == Util.StatusCode.TIMEOUT.value()) {
				//On a timeout error, wait for a time before running the code again
				retryAfter = response.getFirstHeader("retry-after") == null ? null : response.getFirstHeader("retry-after").getValue();
				
				if(retryAfter==null) {
					timeoutWait = Util.initialWait;
				}else {
					try {
						timeoutWait = Integer.parseInt(retryAfter);
						
					}catch(Exception ex){
						timeoutWait = Util.initialWait;
					}
					
					TimeUnit.SECONDS.sleep(timeoutWait);
				}
				
				
			}
			
			if(response!=null) {//Close the connection to the current one to allow for new connections
				EntityUtils.consume(response.getEntity());
			}

			response = MakeRequest.makeRequest(url, request, apiData);
			
			}while(failureCount++ <= Util.maxFailRetries && response.getStatusLine().getStatusCode() != Util.StatusCode.SUCCESS.value());
			
			returnValue = convertToResponseObject(response, responseClass);
			EntityUtils.consume(response.getEntity());//Consume the connection so it closes
			return returnValue;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	private static final CloseableHttpClient  getClient(String url,boolean useProxy) {
		/*if(useProxy && !url.contains("Authenticate")) {
		HttpHost proxy = new HttpHost("127.0.0.1",8888,"https");
		 DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
		 HttpClient client = HttpClientBuilder.create().setRoutePlanner(routePlanner).build();
		 return client;
			return HttpClients.createDefault();
		}else {
			
		}*/
		
		return HttpClients.createDefault();
	}
	
	public static final CloseableHttpResponse makeRequest(String url, APIRequest request, APIData apiData)
			throws ClientProtocolException, IOException {
		 
		 
		if(apiData.client == null) {//Get a client if one doesn't exist
			apiData.client = getClient(url, false);
		}

		HttpPost post = new HttpPost(url);

		post.setHeader("x-api-key", apiData.apiKey);
		post.setHeader("accept", "application/json");
		post.setHeader("Content-Type", "text/json");

		/*if (apiData.isAuthenticated()) {
			post.setHeader("cookie", apiData.cookie.toString());
		}*/
		String jsonRequest = request.toJSON();
		post.setEntity(new StringEntity(jsonRequest));
		return apiData.client.execute(post);
	}

	public static <T extends APIResponse> T convertToResponseObject(HttpResponse response, Class<T> responseClass)
			throws IllegalStateException, IOException {
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		ObjectMapper mapper = new ObjectMapper();
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return mapper.readValue(result.toString(), responseClass);
	}

	/**
	 * Converts the url path to the format required
	 * 
	 * @param path - The path provided
	 * @return - Modified url path so it contains the correct information
	 */
	public static String modifyURLPath(String path) {

		if (path.startsWith("/")) {
			path = path.substring(1, path.length());
		}
		return path;

	}
}
