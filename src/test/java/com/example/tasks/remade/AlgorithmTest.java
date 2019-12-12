package com.example.tasks.remade;

import com.example.tasks.task_two.remade.Algorithms;
import com.example.tasks.task_two.remade2.RemadeAlgorithms;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlgorithmTest {

    private String string1 = "hello";
    private String string2 = "healo";
    private String string3 = "abc";
    private String string4 = "oab";

    @Test
    public void recursionTest() {
        Assert.assertEquals("h*e*ll*o", Algorithms.newRecursiveMethod(string1));
        Assert.assertEquals("h*e*a*l*o", Algorithms.newRecursiveMethod(string2));
        Assert.assertEquals("a*bc", Algorithms.newRecursiveMethod(string3));
        Assert.assertEquals("o*a*b", Algorithms.newRecursiveMethod(string4));

    }

    @Test
    public void recursionWithHashTest() {
        Assert.assertEquals("h*e*ll*o", Algorithms.newRecursiveMethodTest(string1));
        Assert.assertEquals("h*e*a*l*o", Algorithms.newRecursiveMethodTest(string2));
        Assert.assertEquals("a*bc", Algorithms.newRecursiveMethodTest(string3));
        Assert.assertEquals("o*a*b", Algorithms.newRecursiveMethodTest(string4));

    }

    @Test
    public void messageCodingTest() {
        String signal1 = "01000111";
        String signal2 = "101101010111";
        Assert.assertEquals("00 0 0 0 00 000 0 000", Algorithms.messageCoding(signal1));
        Assert.assertEquals("0 0 00 0 0 00 00 0 0 0 00 0 0 0 00 0 0 000", Algorithms.messageCoding(signal2));
    }

    @Test
    public void recTest() {
        Assert.assertEquals("h*e*ll*o", RemadeAlgorithms.remadeRecursion(string1));
        Assert.assertEquals("h*e*a*l*o", RemadeAlgorithms.remadeRecursion(string2));
        Assert.assertEquals("a*bc", RemadeAlgorithms.remadeRecursion(string3));
        Assert.assertEquals("o*a*b", RemadeAlgorithms.remadeRecursion(string4));
    }

    @Test
    public void lastRecursionTest(){
        Assert.assertEquals("h*e*ll*o", RemadeAlgorithms.lastRecursion(string1));
        Assert.assertEquals("h*e*a*l*o", RemadeAlgorithms.lastRecursion(string2));
        Assert.assertEquals("a*bc", RemadeAlgorithms.lastRecursion(string3));
        Assert.assertEquals("o*a*b", RemadeAlgorithms.lastRecursion(string4));
    }
}
