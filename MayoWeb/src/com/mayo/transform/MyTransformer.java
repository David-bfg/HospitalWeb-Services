package com.mayo.transform;

import java.io.File;
import java.io.StringReader;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.dom4j.Document;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

public class MyTransformer {

	public static MyTransformer instance = null;
	

	public String styleDocument(String xml, String xslt) {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new File(xml));

			// create an instance of TransformerFactory
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			// initialize Transformer
			Transformer transformer = null;
			try {
				transformer = transformerFactory
						.newTransformer(new StreamSource(new File(xslt)));
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			Document finalDoc = null;
			if (null != transformer) {
				DocumentSource source = new DocumentSource(document);
				DocumentResult result = new DocumentResult();
				// transform the source to the document result
				transformer.transform(source, result);

				// return the transformed document
				finalDoc = result.getDocument();
				return finalDoc.asXML().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","").trim();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	public String styleDocumentFromData(String xmlData, String xslt) {
		SAXReader saxReader = new SAXReader();
		Document document;
		try {
			document = saxReader.read(new InputSource(new StringReader(xmlData)));

			// create an instance of TransformerFactory
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			// initialize Transformer
			Transformer transformer = null;
			try {
				transformer = transformerFactory
						.newTransformer(new StreamSource(new File(xslt)));
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			}
			Document finalDoc = null;
			if (null != transformer) {
				DocumentSource source = new DocumentSource(document);
				DocumentResult result = new DocumentResult();
				// transform the source to the document result
				transformer.transform(source, result);

				// return the transformed document
				finalDoc = result.getDocument();
				return finalDoc.asXML().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","").trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static MyTransformer getInstance() {
		if(instance==null)
			instance = new MyTransformer();
		
		return instance;
	}
	
	public static void main(String [] args)
	{
		System.out.println(MyTransformer.getInstance().styleDocument("test.xml", "patient.xsl"));
	}
}
