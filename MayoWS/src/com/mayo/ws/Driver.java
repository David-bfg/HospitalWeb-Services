package com.mayo.ws;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

public class Driver {
	public static void main(String[] args) throws IOException {

		String baseUri = "http://localhost:8081/ws/";
		
		File xml = new File("conf.xml");
		Document doc;
		SAXReader reader = new SAXReader();
		try {
			doc = reader.read(xml);
			Node n = doc.selectSingleNode("//MayoWS/ws_url");
			String url = n.getText();
			System.out.println(url);
			baseUri = url;
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages",
				"com.mayo.ws");

		System.out.println("Starting grizzly...");
		SelectorThread threadSelector = GrizzlyWebContainerFactory.create(
				baseUri, initParams);
		System.out.println(String.format(
				"Jersey app started with WADL available at %sapplication.wadl\n"
						+ "Try out %sPatient\nHit enter to stop it...",
				baseUri, baseUri));
		System.in.read();
		threadSelector.stopEndpoint();
		System.exit(0);
	}
}
