package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.XMLConstats;
import com.epam.rd.java.basic.practice7.entity.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import javax.xml.transform.stream.StreamSource;
import java.math.BigInteger;
import java.util.Objects;

public class STAXParser extends DefaultHandler {
    private final String  xmlFileName;

    private String currentElement; // <-- current element name holder

    private Flowers flowers2; // <-- main container
    private Flower flower2;
    private GrowingTips growingTips2;
    private VisualParameters visualParameters2;

    public Flowers getFlowers(){return flowers2;}

    public STAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse() throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
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
                startTags(startElement);
            }

            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();
                contentHandler(characters);
            }

            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();
                endTags(localName);
            }
        }
        reader.close();
    }
    public void startTags(StartElement startElement){
        if(Objects.equals(currentElement, XMLConstats.FLOWERS.value())){
            flowers2 = new Flowers();
        }else
        if(Objects.equals(currentElement, XMLConstats.FLOWER.value())){
            flower2 = new Flower();
        }else
        if(Objects.equals(currentElement, XMLConstats.VISUALPARAMETERS.value())){
            visualParameters2 = new VisualParameters();
        }else
        if(Objects.equals(currentElement, XMLConstats.GROWINGTIPS.value())){
            growingTips2 = new GrowingTips();
        }else
        if(Objects.equals(currentElement, XMLConstats.AVELENFLOWER.value())){
            visualParameters2.setAveLenFlower(new AveLenFlower());
            Attribute attribute = startElement.getAttributeByName(
                    new QName(XMLConstats.MEASURE.value()));
            if (attribute != null)
                visualParameters2.getAveLenFlower().setMeasure(attribute.getValue());
        }else
            startTagsGrowingTips(startElement);
    }

    public void startTagsGrowingTips(StartElement startElement){
        if(Objects.equals(currentElement, XMLConstats.TEMPERETURE.value())){
            growingTips2.setTempreture(new Tempreture());
            Attribute attribute = startElement.getAttributeByName(
                    new QName(XMLConstats.MEASURE.value()));
            if (attribute != null)
                growingTips2.getTempreture().setMeasure(attribute.getValue());
        }else
        if(Objects.equals(currentElement, XMLConstats.LIGHTNING.value())){
            growingTips2.setLighting(new Lighting());
            Attribute attribute = startElement.getAttributeByName(
                    new QName(XMLConstats.LIGHTREQUIRING.value()));
            if (attribute != null)
                growingTips2.getLighting().setLightRequiring(attribute.getValue());
        }else
        if(Objects.equals(currentElement, XMLConstats.WATERING.value())){
            growingTips2.setWatering(new Watering());
            Attribute attribute = startElement.getAttributeByName(
                    new QName(XMLConstats.MEASURE.value()));
            if (attribute != null)
                growingTips2.getWatering().setMeasure(attribute.getValue());
        }
    }

    public void endTags(String localName){
        if(Objects.equals(localName, XMLConstats.FLOWER.value())){
            flowers2.getFlower().add(flower2);
        }else
        if(Objects.equals(localName, XMLConstats.VISUALPARAMETERS.value())){
            flower2.setVisualParameters(visualParameters2);
        }else
        if(Objects.equals(localName, XMLConstats.GROWINGTIPS.value())){
            flower2.setGrowingTips(growingTips2);
        }
    }

    public void contentHandler(Characters characters){
        if (Objects.equals(currentElement, XMLConstats.NAME.value())) {
            flower2.setName(characters.getData());
        }else
        if (Objects.equals(currentElement, XMLConstats.SOIL.value())) {
            flower2.setSoil(characters.getData());
        }else
        if (Objects.equals(currentElement, XMLConstats.ORIGIN.value())) {
            flower2.setOrigin(characters.getData());
        }else
        if(Objects.equals(currentElement, XMLConstats.STEAMCOLOUR.value())){
            visualParameters2.setStemColour(characters.getData());
        }else
        if(Objects.equals(currentElement, XMLConstats.LEAFCOLOUR.value())){
            visualParameters2.setLeafColour(characters.getData());
        }else
        if(Objects.equals(currentElement, XMLConstats.AVELENFLOWER.value())){
            visualParameters2.getAveLenFlower().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
        }else
        if(Objects.equals(currentElement, XMLConstats.TEMPERETURE.value())){
            growingTips2.getTempreture().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
        }else
        if(Objects.equals(currentElement, XMLConstats.WATERING.value())){
            growingTips2.getWatering().setValue(BigInteger.valueOf(Integer.parseInt(characters.getData())));
        }else
        if(Objects.equals(currentElement, XMLConstats.MULTIPLYING.value())){
            flower2.setMultiplying(characters.getData());
        }
    }
}
