package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value2"
})
public class AveLenFlower {

    @XmlValue
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger value2;
    @XmlAttribute(name = "measure", required = true)
    protected String measure2;

    public BigInteger getValue() {
        return value2;
    }

    public void setValue(BigInteger value) {
        this.value2 = value;
    }

    public String getMeasure() {
        if (this.measure2 == null) {
            return "cm";
        } else {
            return this.measure2;
        }
    }

    public void setMeasure(String value) {
        this.measure2 = value;
    }

}
