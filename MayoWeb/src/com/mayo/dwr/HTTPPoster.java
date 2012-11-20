package com.mayo.dwr;

import java.io.File;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.mayo.transform.Files;


public class HTTPPoster {

	String urlString = "http://localhost:8081/ws/";
	public static HTTPPoster instance = null;

	public HTTPPoster() {
		String conf = Files.getInstance().CONF;
		initURL(conf);
	}
	
	public void initURL(String conf) {
		File xml = new File(conf);
		Document doc;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(xml);
			Node n = doc.selectSingleNode("//conf/ws_url");
			String url = n.getText();
			System.out.println(url);
			urlString = url;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

	public String post(String uri, String data) {
		String res = "";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(urlString + uri);
		method.setRequestBody(data);

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			res = method.getResponseBodyAsString();

		} catch (Exception e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return res;
	}

	public String get(String uri) {
		String res = "";
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod(urlString + uri);

		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();
			res = (new String(responseBody));

		} catch (Exception e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return res;
	}

	public String put(String uri, String data) {
		String res = "";
		HttpClient client = new HttpClient();
		PutMethod method = new PutMethod(urlString + uri);
		method.setRequestBody(data);

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			res = method.getResponseBodyAsString();

		} catch (Exception e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return res;
	}

	public String delete(String uri) {
		String res = "";
		HttpClient client = new HttpClient();
		DeleteMethod method = new DeleteMethod(urlString + uri);

		try {
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();
			res = (new String(responseBody));

		} catch (Exception e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
		return res;
	}

	public static HTTPPoster getInstance() {
		if (instance == null)
			instance = new HTTPPoster();

		return instance;
	}

	public static void main(String[] args) {
		HTTPPoster hp = new HTTPPoster();
		System.out.println(hp.get("Test/1"));
		System.out.println(hp.post("Test/1", "data"));
		System.out.println(hp.put("Test/1", "data"));
		System.out.println(hp.delete("Test/1"));
	}
}
