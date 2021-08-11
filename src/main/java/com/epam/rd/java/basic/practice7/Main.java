package com.epam.rd.java.basic.practice7;

import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.parsers.DOMParser;
import com.epam.rd.java.basic.practice7.parsers.SAXParser;
import com.epam.rd.java.basic.practice7.parsers.STAXParser;
import com.epam.rd.java.basic.practice7.util.Sorting;

public final class Main {

    public static void main(final String[] args) throws Exception {
        String xmlFileName = "input.xml";

        ///////////////////////////////////////
        ///         DOM parser              ///
        ///////////////////////////////////////
        DOMParser domController = new DOMParser(xmlFileName);
        domController.parse(true);

        Flowers flowers = domController.getFlowers();
        Sorting.setSortFlowersByAveLen(flowers);

        String outputXmlFile = "output.dom.xml";
        DOMParser.saveXML(flowers, outputXmlFile);

        ///////////////////////////////////////
        ///         SAX parser              ///
        ///////////////////////////////////////
        SAXParser saxController = new SAXParser(xmlFileName);
        saxController.parse(true);
        flowers = saxController.getFlowers();

        Sorting.setSortFlowersByFlowersName(flowers);

        outputXmlFile = "output.sax.xml";
        DOMParser.saveXML(flowers, outputXmlFile);

        ///////////////////////////////////////
        ///         StAX parser             ///
        ///////////////////////////////////////
        STAXParser staxController = new STAXParser(xmlFileName);
        staxController.parse();
        flowers = staxController.getFlowers();

        Sorting.setSortFlowersByWateringMeasure(flowers);

        outputXmlFile = "output.stax.xml";
        DOMParser.saveXML(flowers, outputXmlFile);
    }
}
