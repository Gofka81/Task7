package com.epam.rd.java.basic.practice7.parsers;

import com.epam.rd.java.basic.practice7.entity.Flower;
import com.epam.rd.java.basic.practice7.entity.Flowers;
import com.epam.rd.java.basic.practice7.util.Sorting;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class DOMParserTest {
    @Test
    public void isWorkingWithValidXML(){
        String xmlFileName = "input.xml";
        DOMParser domController = new DOMParser(xmlFileName);
        try {
            domController.parse(true);
            Flowers flowers = domController.getFlowers();
            Sorting.setSortFlowersByFlowersName(flowers);

            boolean check = true;

            Flower flower = flowers.getFlower().get(0);
            if(!flower.getName().equals("Bambusa") && !flower.getSoil().equals("дерново-подзолистая")&&!flower.getOrigin().equals("China")){
                check = false;
            }
            if(!flower.getVisualParameters().getAveLenFlower().getMeasure().equals("cm") && !flower.getVisualParameters().getAveLenFlower().getValue().equals(BigInteger.valueOf(1100))){
                check = false;
            }
            if(!flower.getVisualParameters().getLeafColour().equals("green") &&!flower.getVisualParameters().getStemColour().equals("green")){
                check = false;
            }
            Assert.assertTrue(check);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
