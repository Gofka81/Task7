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
    protected Tempreture tempreture;
    @XmlElement(namespace = "http://www.nure.ua", required = true)
    protected Lighting lighting;
    @XmlElement(namespace = "http://www.nure.ua")
    protected Watering watering;


    public Tempreture getTempreture() {
        return tempreture;
    }

    public void setTempreture(Tempreture value) {
        this.tempreture = value;
    }

    public Lighting getLighting() {
        return lighting;
    }

    public void setLighting(Lighting value) {
        this.lighting = value;
    }

    public Watering getWatering() {
        return watering;
    }

    public void setWatering(Watering value) {
        this.watering = value;
    }

}
