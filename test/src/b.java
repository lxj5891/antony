import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class b {
	public static void main(String[] args) {
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			String u = "http://hpwapv.dskcc.net/approval/service/document.asmx/DocListForMyService?userID=06534&title=&pageNumber=&pageSize=";
			URL url = new URL(u);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			Document doc = db.parse(urlConnection.getInputStream());
			NodeList elements = doc.getElementsByTagName("string");
			System.out.println(elements.getLength());
			Element elmtInfo = doc.getDocumentElement();
			NodeList nodes = elmtInfo.getChildNodes();
			NodeList docs = null;
			for (int i = 0; i < nodes.getLength(); i++) {
				Node result = nodes.item(i);
				String dr = result.toString();
				System.out.println(dr);
				Document ddoc = db.parse(new ByteArrayInputStream(dr
						.getBytes("UTF-8")));
				NodeList es = ddoc.getElementsByTagName("Document");
				System.out.println(es.getLength());
				docs = result.getChildNodes();
				System.out.println(docs.getLength());
				for (int j = 0; j < es.getLength(); j++) {
					Node item = es.item(j);
					System.out.println("----------------------");
					NodeList childNodes = item.getChildNodes();

					for (int k = 0; k < childNodes.getLength(); k++) {
						Node item2 = childNodes.item(k);

						String string = item2.toString();
						// System.out.println(item2.getNodeName().toString()
						// .length()
						// + ">>" + string.length());
						try {
							System.out.println(item2.getNodeName()
									+ ":"
									+ string.substring(item2.getNodeName()
											.toString().length() + 2,
											string.length()
													- item2.getNodeName()
															.toString()
															.length() - 3));
						} catch (Exception e) {
							// TODO Auto-generated catch block
						}
					}
					System.out.println("----------------------");
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}