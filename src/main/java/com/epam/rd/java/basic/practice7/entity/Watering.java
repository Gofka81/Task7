package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value1"
})
public class Watering {

    @XmlValue
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger value1;
    @XmlAttribute(name = "measure", required = true)
    protected String measure1;

    public BigInteger getValue() {
        return value1;
    }

    public void setValue(BigInteger value) {
        this.value1 = value;
    }

    public String getMeasure() {
        if (measure1 == null) {
            return "mlPerWeek";
        } else {
            return measure1;
        }
    }

    public void setMeasure(String value) {
        this.measure1 = value;
    }

}
