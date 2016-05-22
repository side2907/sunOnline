package admin;

import simple.Simple;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XML {
	static File ahelp = new File("./src/admin/ahelp.xml");
	static File uhelp = new File("./src/admin/uhelp.xml");
	static DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
	static Simple S = new Simple();
	
	public static String Help(String a){ //вывод справки по командам администратора
        String s = new String("\n");
        File f = null;
        if (a=="ahelp")f = ahelp;
        if (a=="uhelp")f = uhelp;
		try {
		    DocumentBuilder db=dbf.newDocumentBuilder();
		    Document doc=db.parse(f);
            doc.getDocumentElement().normalize();
            NodeList nodeList=doc.getElementsByTagName("command");
            for (int i=0;i<nodeList.getLength();i++){
            	Node fstNode=nodeList.item(i);
                if(fstNode.getNodeType()==Node.ELEMENT_NODE){
                    Element el=(Element)fstNode;
                    NodeList nlx=el.getElementsByTagName("type");
                    Element elx=(Element)nlx.item(0);
                    NodeList nlxc=elx.getChildNodes();
                    NodeList nly=el.getElementsByTagName("desc");
                    Element ely=(Element)nly.item(0);
                    NodeList nlyc=ely.getChildNodes();
                    s+=S.MaxLength(((Node)nlxc.item(0)).getNodeValue()+((Node)nlyc.item(0)).getNodeValue())+"\n";
                }
            }	
		}catch(Exception e){
			s = "ERROR!";
			e.printStackTrace();
		}
		return s;
	}
}
