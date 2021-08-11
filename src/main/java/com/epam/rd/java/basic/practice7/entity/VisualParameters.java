package com.epam.rd.java.basic.practice7.entity;

import org.w3c.dom.Node;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "stemColour",
        "leafColour",
        "aveLenFlower"
})
public class VisualParameters {

    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String stemColour;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String leafColour;
    @XmlElement(namespace = "http://www.nure.ua")
    protected VisualParameters.AveLenFlower aveLenFlower;

    public String getStemColour() {
        return stemColour;
    }

    public void setStemColour(String value) {
        this.stemColour = value;
    }

    public String getLeafColour() {
        return leafColour;
    }

    public void setLeafColour(String value) {
        this.leafColour = value;
    }

    public VisualParameters.AveLenFlower getAveLenFlower() {
        return aveLenFlower;
    }

    public void setAveLenFlower(VisualParameters.AveLenFlower value) {
        this.aveLenFlower = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class AveLenFlower {

        @XmlValue
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger value;
        @XmlAttribute(name = "measure", required = true)
        protected String measure;

        public BigInteger getValue() {
            return value;
        }

        public void setValue(BigInteger value) {
            this.value = value;
        }

        public String getMeasure() {
            if (this.measure == null) {
                return "cm";
            } else {
                return this.measure;
            }
        }

        public void setMeasure(String value) {
            this.measure = value;
        }

    }

}
