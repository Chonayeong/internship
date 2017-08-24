package com.trumpia.shortcodetester.inbound;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.CharacterData;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ReadXML {

	public Map<String, String> Read(String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(new InputSource(new StringReader(xml)));
		Element root = doc.getDocumentElement();
		Node node = root.getFirstChild();
		HashMap<String,String> nodes = new HashMap<String,String>();
		while(node!=null) {
			nodes.put(node.getNodeName(), node.getTextContent());
			node= node.getNextSibling();
		}
		return nodes;
		
	}
}
