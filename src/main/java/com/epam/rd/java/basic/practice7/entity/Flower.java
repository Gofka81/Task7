package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "name",
        "soil",
        "origin",
        "visualParameters",
        "growingTips",
        "multiplying"
})
public class Flower {

    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String name;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String soil;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String origin;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected VisualParameters visualParameters;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected GrowingTips growingTips;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected String multiplying;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String value) {
        this.soil = value;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String value) {
        this.origin = value;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public void setVisualParameters(VisualParameters value) {
        this.visualParameters = value;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips value) {
        this.growingTips = value;
    }

    public String getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(String value) {
        this.multiplying = value;
    }
}
