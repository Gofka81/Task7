package com.epam.rd.java.basic.practice7.entity;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "tempreture",
        "lighting",
        "watering"
})
public class GrowingTips {

    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected GrowingTips.Tempreture tempreture;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected GrowingTips.Lighting lighting;
    @XmlElement(namespace = "http://www.nure.ua")
    protected GrowingTips.Watering watering;


    public GrowingTips.Tempreture getTempreture() {
        return tempreture;
    }

    public void setTempreture(GrowingTips.Tempreture value) {
        this.tempreture = value;
    }

    public GrowingTips.Lighting getLighting() {
        return lighting;
    }

    public void setLighting(GrowingTips.Lighting value) {
        this.lighting = value;
    }

    public GrowingTips.Watering getWatering() {
        return watering;
    }

    public void setWatering(GrowingTips.Watering value) {
        this.watering = value;
    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Lighting {

        @XmlAttribute(name = "lightRequiring", required = true)
        protected String lightRequiring;

        public String getLightRequiring() {
            return lightRequiring;
        }

        public void setLightRequiring(String value) {
            this.lightRequiring = value;
        }

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Tempreture {

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
            if (measure == null) {
                return "celcius";
            } else {
                return measure;
            }
        }

        public void setMeasure(String value) {
            this.measure = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class Watering {

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
            if (measure == null) {
                return "mlPerWeek";
            } else {
                return measure;
            }
        }

        public void setMeasure(String value) {
            this.measure = value;
        }

    }

}
