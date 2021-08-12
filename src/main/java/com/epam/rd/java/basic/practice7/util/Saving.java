package com.epam.rd.java.basic.practice7.util;

import org.w3c.dom.Document;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class Saving {

    private Saving(){
    }

    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        t.transform(new DOMSource(document), result);
    }
}
