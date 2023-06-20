package org.tugas;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    void shouldReturnData() {
        var list = new ArrayList<String>();
        list.add("test");
        assertEquals("test", list.get(0));
    }
}
