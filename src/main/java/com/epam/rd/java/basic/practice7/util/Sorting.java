package com.epam.rd.java.basic.practice7.util;

import com.epam.rd.java.basic.practice7.entity.Flower;
import com.epam.rd.java.basic.practice7.entity.Flowers;

import java.util.Collections;
import java.util.Comparator;

public class Sorting {

    private Sorting() {
        //Blocked creating elements
    }

    public static void setSortFlowersByFlowersName(Flowers flowers){
        Collections.sort(flowers.getFlower(),Comparator.comparing(Flower::getName));
    }

    public static void setSortFlowersByAveLen(Flowers flowers){
        Collections.sort(flowers.getFlower(),Comparator.comparing(o-> o.getVisualParameters().getAveLenFlower().getValue()));
    }

    public static void setSortFlowersByWateringMeasure(Flowers flowers){
        Collections.sort(flowers.getFlower(),Comparator.comparing(o-> o.getGrowingTips().getWatering().getValue()));
    }
}
