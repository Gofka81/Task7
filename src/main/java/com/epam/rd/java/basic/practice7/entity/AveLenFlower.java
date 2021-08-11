package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value"
})
public class AveLenFlower {

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
