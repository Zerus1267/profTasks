package com.example.tasks.remade;

import com.example.tasks.task_two.remade.Algorithms;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AlgorithmTest {

    @Test
    public void recursionTest() {
        String string1 = "hello";
        String string2 = "healo";
        String string3 = "abc";
        String string4 = "oab";
        Assert.assertEquals("h*e*ll*o", Algorithms.newRecursiveMethod(string1));
        Assert.assertEquals("h*e*a*l*o", Algorithms.newRecursiveMethod(string2));
        Assert.assertEquals("a*bc", Algorithms.newRecursiveMethod(string3));
        Assert.assertEquals("o*a*b", Algorithms.newRecursiveMethod(string4));
    }

    @Test
    public void messageCodingTest() {
        String signal1 = "01000111";
        String signal2 = "101101010111";
        Assert.assertEquals("00 0 0 0 00 000 0 000", Algorithms.messageCoding(signal1));
        Assert.assertEquals("0 0 00 0 0 00 00 0 0 0 00 0 0 0 00 0 0 000", Algorithms.messageCoding(signal2));
    }

}
