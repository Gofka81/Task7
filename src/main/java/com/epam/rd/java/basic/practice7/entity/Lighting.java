package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
public class Lighting {

    @XmlAttribute(name = "lightRequiring", required = true)
    protected String lightRequiring;

    public String getLightRequiring() {
        return lightRequiring;
    }

    public void setLightRequiring(String value) {
        this.lightRequiring = value;
    }

}
