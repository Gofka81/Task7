package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.XMLConstats;
import com.epam.rd.java.basic.practice7.entity.*;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.math.BigInteger;

public class STAXParser extends DefaultHandler {
    private String xmlFileName;

    private String currentElement; // <-- current element name holder

    private Flowers flowers; // <-- main container
    private Flower flower;
    private GrowingTips growingTips;
    private VisualParameters visualParameters;

    public Flowers getFlowers(){return flowers;}

    public STAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws ParserConfigurationException, SAXException,
            IOException, XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            // skip any empty content
            if (event.isCharacters() && event.asCharacters().isWhiteSpace())
                continue;

            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                // WARNING!!!
                // here and below we use '==' operation to compare two INTERNED STRINGS
                if(currentElement == XMLConstats.FLOWERS.value()){
                    flowers = new Flowers();
                }else
                if(currentElement == XMLConstats.FLOWER.value()){
                    flower = new Flower();
                }else
                if(currentElement == XMLConstats.VISUALPARAMETERS.value()){
                    visualParameters = new VisualParameters();
                }else
                if(currentElement == XMLConstats.GROWINGTIPS.value()){
                    growingTips = new GrowingTips();
                }else
                if(currentElement == XMLConstats.AVELENFLOWER.value()){
                    visualParameters.setAveLenFlower(new AveLenFlower());
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XMLConstats.MEASURE.value()));
                    if (attribute != null)
                        visualParameters.getAveLenFlower().setMeasure(attribute.getValue());
                }else
                if(currentElement == XMLConstats.TEMPERETURE.value()){
                    growingTips.setTempreture(new Tempreture());
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XMLConstats.MEASURE.value()));
                    if (attribute != null)
                        growingTips.getTempreture().setMeasure(attribute.getValue());
                }else
                if(currentElement == XMLConstats.LIGHTNING.value()){
                    growingTips.setLighting(new Lighting());
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XMLConstats.LIGHTREQUIRING.value()));
                    if (attribute != null)
                        growingTips.getLighting().setLightRequiring(attribute.getValue());
                }else
                if(currentElement == XMLConstats.WATERING.value()){
                    growingTips.setWatering(new Watering());
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(XMLConstats.MEASURE.value()));
                    if (attribute != null)
                        growingTips.getWatering().setMeasure(attribute.getValue());
                }
            }

            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (currentElement == XMLConstats.NAME.value()) {
                    flower.setName(characters.getData());
                }else
                if (currentElement == XMLConstats.SOIL.value()) {
                    flower.setSoil(characters.getData());
                }else
                if (currentElement == XMLConstats.ORIGIN.value()) {
                    flower.setOrigin(characters.getData());
                }else
                if(currentElement == XMLConstats.STEAMCOLOUR.value()){
                    visualParameters.setStemColour(characters.getData());
                }else
                if(currentElement == XMLConstats.LEAFCOLOUR.value()){
                    visualParameters.setLeafColour(characters.getData());
                }else
                if(currentElement == XMLConstats.AVELENFLOWER.value()){
                    visualParameters.getAveLenFlower().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
                }else
                if(currentElement == XMLConstats.TEMPERETURE.value()){
                    growingTips.getTempreture().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
                }else
                if(currentElement == XMLConstats.WATERING.value()){
                    growingTips.getWatering().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
                }else
                if(currentElement == XMLConstats.MULTIPLYING.value()){
                    flower.setMultiplying(characters.getData());
                }
            }

            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if(localName == XMLConstats.FLOWER.value()){
                    flowers.getFlower().add(flower);
                }else
                if(localName == XMLConstats.VISUALPARAMETERS.value()){
                    flower.setVisualParameters(visualParameters);
                }else
                if(localName == XMLConstats.GROWINGTIPS.value()){
                    flower.setGrowingTips(growingTips);
                }
            }
        }
        reader.close();
    }
}
