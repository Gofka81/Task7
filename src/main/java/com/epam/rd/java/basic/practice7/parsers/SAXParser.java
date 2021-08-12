package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.constants.Constants;
import com.epam.rd.java.basic.practice7.constants.XMLConstats;
import com.epam.rd.java.basic.practice7.entity.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;

public class SAXParser extends DefaultHandler {
    private String xmlFileName;

    public SAXParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void parse(boolean validate) throws SAXException,
            ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance(
                Constants.CLASS_SAX_PARSER_FACTORY_INTERNAL,
                this.getClass().getClassLoader());

        factory.setNamespaceAware(true);
        if(validate){
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        javax.xml.parsers.SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    private String currentElement; // <-- current element name holder

    private Flowers flowers; // <-- main container
    private Flower flower;
    private GrowingTips growingTips;
    private VisualParameters visualParameters;

    public Flowers getFlowers(){return flowers;}


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = localName;

        if(Objects.equals(currentElement, XMLConstats.FLOWERS.value())){
            flowers = new Flowers();
        }else
        if(Objects.equals(currentElement, XMLConstats.FLOWER.value())){
            flower = new Flower();
        }else
        if(Objects.equals(currentElement, XMLConstats.VISUALPARAMETERS.value())){
            visualParameters = new VisualParameters();
        }else
        if(Objects.equals(currentElement, XMLConstats.GROWINGTIPS.value())){
            growingTips = new GrowingTips();
        }else
        if(Objects.equals(currentElement, XMLConstats.AVELENFLOWER.value())){
            visualParameters.setAveLenFlower(new AveLenFlower());
            if(attributes.getLength()> 0){
                visualParameters.getAveLenFlower().setMeasure(attributes.getValue(uri,
                        XMLConstats.MEASURE.value()));
            }
        }else
            checkGrowingTips(uri,attributes);
    }

    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#endElement
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(Objects.equals(localName, XMLConstats.FLOWER.value())){
            flowers.getFlower().add(flower);
        }else
        if(Objects.equals(localName, XMLConstats.VISUALPARAMETERS.value())){
            flower.setVisualParameters(visualParameters);
        }else
        if(Objects.equals(localName, XMLConstats.GROWINGTIPS.value())){
            flower.setGrowingTips(growingTips);
        }

    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     * @throws SAXException Any SAX exception, possibly
     *                      wrapping another exception.
     * @see ContentHandler#characters
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch, start, length).trim();

        if (elementText.isEmpty()) // <-- return if content is empty
            return;

        if (Objects.equals(currentElement, XMLConstats.NAME.value())) {
            flower.setName(elementText);
        }else
        if (Objects.equals(currentElement, XMLConstats.SOIL.value())) {
            flower.setSoil(elementText);
        }else
        if (Objects.equals(currentElement, XMLConstats.ORIGIN.value())) {
            flower.setOrigin(elementText);
        }else
        if(Objects.equals(currentElement, XMLConstats.STEAMCOLOUR.value())){
            visualParameters.setStemColour(elementText);
        }else
        if(Objects.equals(currentElement, XMLConstats.LEAFCOLOUR.value())){
            visualParameters.setLeafColour(elementText);
        }else
        if(Objects.equals(currentElement, XMLConstats.AVELENFLOWER.value())){
            visualParameters.getAveLenFlower().setValue(BigInteger.valueOf(Integer.parseInt(elementText)));
        }else
        if(Objects.equals(currentElement, XMLConstats.TEMPERETURE.value())){
            growingTips.getTempreture().setValue(BigInteger.valueOf(Integer.parseInt(elementText)));
        }else
        if(Objects.equals(currentElement, XMLConstats.WATERING.value())){
            growingTips.getWatering().setValue(BigInteger.valueOf(Integer.parseInt(elementText)));
        }else
        if(Objects.equals(currentElement, XMLConstats.MULTIPLYING.value())){
            flower.setMultiplying(elementText);
        }
    }

    public void checkGrowingTips(String uri,Attributes attributes){
        if(Objects.equals(currentElement, XMLConstats.TEMPERETURE.value())){
            growingTips.setTempreture(new Tempreture());
            if(attributes.getLength()> 0){
                growingTips.getTempreture().setMeasure(attributes.getValue(uri,
                        XMLConstats.MEASURE.value()));
            }
        }else
        if(Objects.equals(currentElement, XMLConstats.LIGHTNING.value())){
            growingTips.setLighting(new Lighting());
            if(attributes.getLength()> 0){
                growingTips.getLighting().setLightRequiring(attributes.getValue(0));
            }
        }else
        if(Objects.equals(currentElement, XMLConstats.WATERING.value())){
            growingTips.setWatering(new Watering());
            if(attributes.getLength()> 0){
                growingTips.getWatering().setMeasure(attributes.getValue(uri,
                        XMLConstats.WATERING.value()));
            }
        }
    }
}
