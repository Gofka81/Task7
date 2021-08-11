package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;

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
    protected AveLenFlower aveLenFlower;

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

    public AveLenFlower getAveLenFlower() {
        return aveLenFlower;
    }

    public void setAveLenFlower(AveLenFlower value) {
        this.aveLenFlower = value;
    }


}
