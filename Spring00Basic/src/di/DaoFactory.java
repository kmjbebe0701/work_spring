package di;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DaoFactory {
	
	private static Map<String, String> daos = new HashMap<>();
	
	static {
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser;
			parser = factory.newDocumentBuilder();
			
			Document dom = parser.parse(new FileInputStream("./src/di/config.xml"));
			Element root = dom.getDocumentElement();
			NodeList list = root.getChildNodes();
			for(int i = 0; i < list.getLength(); i++) {
				if (list.item(i) instanceof Element) {
					Element el = (Element) list.item(i);
					String id = el.getAttribute("id");
					String className = el.getTextContent();
					daos.put(id, className);
					System.out.println(id +":" + className );
				}
			}
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
