package com.interview.intercomsubmission;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalculateDistanceTest {

    @Test
    public void calculateDistance1() {
       // double input1 = 53.521111;
        double input1 = 53.0033946;
       // double input2 = -9.831111;
        double input2 = -6.3877505;
        double expected = 38;
        double delta = .1;
        double output;

        MainActivity activity = new MainActivity();
        output = activity.calculateDistance(input1, input2);
        //assertSame(expected, output);
        assertEquals(expected, output, delta);
    }

    @Test
    public void extractData() {

    }
}