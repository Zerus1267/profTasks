/*
* NOT FINISHED YET
* */

package com.example.tasks;

import com.example.tasks.task_one.Address;
import com.example.tasks.task_one.Manager;
import com.example.tasks.task_one.Programmer;
import com.example.tasks.task_one.Worker;
import com.example.tasks.task_two.Algorithms;
import com.example.tasks.task_two.Scripts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestTackTwo {



    @Test
    public void stringVowelWithAsterisk(){
        String string1 = "hello";
        String string2 = "healo";
        String string3 = "abc";
        String string4 = "oab";

        Assert.assertEquals("h*e*ll*o", Algorithms.interviewRecursionTest(string1));
        Assert.assertEquals("h*e*a*l*o", Algorithms.interviewRecursionTest(string2));
        Assert.assertEquals("a*bc", Algorithms.interviewRecursionTest(string3));
        Assert.assertEquals("o*a*b", Algorithms.interviewRecursionTest(string4));
    }

    @Test
    public void codingBinarySignalsToZeroes(){
        String signal1 = "01000111";
        String signal2 = "101101010111";

        Assert.assertEquals("00 0 0 0 00 000 0 000", Algorithms.messageCoding(signal1));
        Assert.assertEquals("0 0 00 0 0 00 00 0 0 0 00 0 0 0 00 0 0 000", Algorithms.messageCoding(signal2));
    }


    @Test
    public void createDBTablesTest() throws SQLException {
        Scripts scripts = new Scripts();
        scripts.createTables();
    }

    void insertTestData(){

    }

}
