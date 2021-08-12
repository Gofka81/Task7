package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.XMLConstats;
import com.epam.rd.java.basic.practice7.entity.*;
import com.epam.rd.java.basic.practice7.util.Saving;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.math.BigInteger;


public class DOMParser {

    private String xmlFileName;

    private Flowers flowers;

    public DOMParser(String xmlFileName){
        this.xmlFileName = xmlFileName;
    }

    public Flowers getFlowers() {
        return flowers;
    }

    public void parse(boolean validate) throws ParserConfigurationException,
            SAXException, IOException {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(
                Constants.CLASS__DOCUMENT_BUILDER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());


        dbf.setNamespaceAware(true);
        if (validate) {
            dbf.setFeature(Constants.FEATURE__TURN_VALIDATION_ON, true);
            dbf.setFeature(Constants.FEATURE__TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                throw e;
            }
        });

        Document document = db.parse(xmlFileName);
        Element root = document.getDocumentElement();


        flowers = new Flowers();

        NodeList flowerNodes = root
                .getElementsByTagName(XMLConstats.FLOWER.value());

        for (int j = 0; j < flowerNodes.getLength(); j++) {
            Flower flower = getFlower(flowerNodes.item(j));
            flowers.getFlower().add(flower);
        }
    }

    public static void saveXML(Flowers flowers, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(
                Constants.CLASS__DOCUMENT_BUILDER_FACTORY_INTERNAL,
                DOMParser.class.getClassLoader());


        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();


        Element tElement = document.createElement(XMLConstats.FLOWERS.value());

        document.appendChild(tElement);

        // add questions elements
        for (Flower flower : flowers.getFlower()){
            //flower
            Element fElement = document.createElement(XMLConstats.FLOWER.value());
            tElement.appendChild(fElement);
            //name
            Element nElement = document.createElement(XMLConstats.NAME.value());
            nElement.setTextContent(flower.getName());
            fElement.appendChild(nElement);
            //soil
            Element sElement = document.createElement(XMLConstats.SOIL.value());
            sElement.setTextContent(flower.getSoil());
            fElement.appendChild(sElement);
            //origin
            Element oElement = document.createElement(XMLConstats.ORIGIN.value());
            oElement.setTextContent(flower.getOrigin());
            fElement.appendChild(oElement);
            //visual parameters
            Element vsElement = document.createElement(XMLConstats.VISUALPARAMETERS.value());
            fElement.appendChild(vsElement);
            //stemColour
            Element vsscElement = document.createElement(XMLConstats.STEAMCOLOUR.value());
            vsscElement.setTextContent(flower.getVisualParameters().getStemColour());
            vsElement.appendChild(vsscElement);
            //leafColour
            Element vslcElement = document.createElement(XMLConstats.LEAFCOLOUR.value());
            vslcElement.setTextContent(flower.getVisualParameters().getLeafColour());
            vsElement.appendChild(vslcElement);
            //aveLenFlower
            Element vsaElement = document.createElement(XMLConstats.AVELENFLOWER.value());
            vsaElement.setTextContent(flower.getVisualParameters().getAveLenFlower().getValue().toString());
            vsaElement.setAttribute(XMLConstats.MEASURE.value(), flower.getVisualParameters().getAveLenFlower().getMeasure());
            vsElement.appendChild(vsaElement);
            //growingTips
            Element gtElement = document.createElement(XMLConstats.GROWINGTIPS.value());
            fElement.appendChild(gtElement);
            //tempreture
            Element gttElement = document.createElement(XMLConstats.TEMPERETURE.value());
            gttElement.setTextContent(flower.getGrowingTips().getTempreture().getValue().toString());
            gttElement.setAttribute(XMLConstats.MEASURE.value(), flower.getGrowingTips().getTempreture().getMeasure());
            gtElement.appendChild(gttElement);
            //lighting
            Element gtlElement = document.createElement(XMLConstats.LIGHTNING.value());
            gtlElement.setAttribute(XMLConstats.LIGHTREQUIRING.value(),flower.getGrowingTips().getLighting().getLightRequiring());
            gtElement.appendChild(gtlElement);
            //watering
            Element gtwElement = document.createElement(XMLConstats.WATERING.value());
            gtwElement.setTextContent(flower.getGrowingTips().getWatering().getValue().toString());
            gtwElement.setAttribute(XMLConstats.MEASURE.value(), flower.getGrowingTips().getWatering().getMeasure());
            gtElement.appendChild(gtwElement);
            //multiplying
            Element mElement = document.createElement(XMLConstats.MULTIPLYING.value());
            mElement.setTextContent(flower.getMultiplying());
            fElement.appendChild(mElement);
        }

        Saving.saveToXML(document, xmlFileName); // DOM -> XML
    }

    private Flower getFlower(Node vNode) {
        Flower flower = new Flower();
        Element fElement = (Element) vNode;

        Node qnNode = fElement.getElementsByTagName(XMLConstats.NAME.value()).item(0);
        flower.setName(qnNode.getTextContent());
        Node qsNode = fElement.getElementsByTagName(XMLConstats.SOIL.value()).item(0);
        flower.setSoil(qsNode.getTextContent());
        Node qoNode = fElement.getElementsByTagName(XMLConstats.ORIGIN.value()).item(0);
        flower.setOrigin(qoNode.getTextContent());
        flower.setVisualParameters(getVisualParameters(vNode));
        flower.setGrowingTips(getGrowingTips(vNode));
        flower.setMultiplying(fElement.getElementsByTagName(XMLConstats.MULTIPLYING.value()).item(0).getTextContent());

        return flower;
    }

    private VisualParameters getVisualParameters(Node vNode){
        VisualParameters visualParameters = new VisualParameters();
        Element vElement = (Element) vNode;

        Node scNode = vElement.getElementsByTagName(XMLConstats.STEAMCOLOUR.value()).item(0);
        visualParameters.setStemColour(scNode.getTextContent());
        Node lcNode = vElement.getElementsByTagName(XMLConstats.LEAFCOLOUR.value()).item(0);
        visualParameters.setLeafColour(lcNode.getTextContent());
        visualParameters.setAveLenFlower(getAveLenFlower(vNode));

        return visualParameters;
    }

    private AveLenFlower getAveLenFlower(Node vNode){
        AveLenFlower aveLenFlower = new AveLenFlower();
        Element aElement = (Element) vNode;

        Node node = aElement.getElementsByTagName(XMLConstats.AVELENFLOWER.value()).item(0);
        aveLenFlower.setValue(BigInteger.valueOf(Integer.parseInt(node.getTextContent())));
        aveLenFlower.setMeasure(node.getAttributes().item(0).getTextContent());
        return  aveLenFlower;
    }

    private GrowingTips getGrowingTips(Node vNode){
        GrowingTips growingTips = new GrowingTips();
        Element element = (Element) vNode;

        Node node = element.getElementsByTagName(XMLConstats.TEMPERETURE.value()).item(0);
        growingTips.setTempreture(new Tempreture());
        growingTips.getTempreture().setValue(BigInteger.valueOf(Integer.parseInt(node.getTextContent())));
        growingTips.getTempreture().setMeasure(node.getAttributes().item(0).getTextContent());

        node = element.getElementsByTagName(XMLConstats.LIGHTNING.value()).item(0);
        growingTips.setLighting(new Lighting());
        growingTips.getLighting().setLightRequiring(node.getAttributes().item(0).getTextContent());

        node = element.getElementsByTagName(XMLConstats.WATERING.value()).item(0);
        growingTips.setWatering(new Watering());
        growingTips.getWatering().setValue(BigInteger.valueOf(Integer.parseInt(node.getTextContent())));
        growingTips.getWatering().setMeasure(node.getAttributes().item(0).getTextContent());

        return growingTips;
    }
}
