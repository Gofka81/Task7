package com.epam.rd.java.basic.practice7.constants;

public enum XMLConstats {
    FLOWERS("flowers"), FLOWER("flower"), NAME("name"), SOIL("soil"), ORIGIN("origin"),
    VISUALPARAMETERS("visualParameters"), STEAMCOLOUR("stemColour"), LEAFCOLOUR("leafColour"),
    AVELENFLOWER("aveLenFlower"), GROWINGTIPS("growingTips"), TEMPERETURE("tempreture"),
    LIGHTNING("lighting"), WATERING("watering"), MULTIPLYING("multiplying"),

    MEASURE("measure"),LIGHTREQUIRING("lightRequiring");

    private String value;

    public String value() {
        return value;
    }

    XMLConstats(String value) {
        this.value = value.intern();
    }
}
