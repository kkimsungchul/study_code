package com.nhn.main;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServletServiceTest {

    private static ServletService servletService;

    @BeforeClass
    public static void setup() {
        servletService = new ServletService();

    }

    @Test
    public void testMakeClassName() {
        servletService = new ServletService();

        String fullClassName = "com.nhn.servlet.hello";
        String expected = "com.nhn.servlet.Hello";
        String result = servletService.makeClassName(fullClassName);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMakeClassNameEmpty() {
        servletService = new ServletService();

        String fullClassName = "";
        String expected = null;

        String result = servletService.makeClassName(fullClassName);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testMakeClassNameFavicon() {
        servletService = new ServletService();
        String fullClassName = "com.example.favicon.ico";
        String expected = null;
        String result = servletService.makeClassName(fullClassName);
        Assert.assertEquals(expected, result);

    }

    @Test
    public void testClassMapping() {
        servletService = new ServletService();

        String fullMappingURL = "com.nhn.servlet.Hello";

        Object result = servletService.classMapping(fullMappingURL);

        Assert.assertNotNull(result);

    }

    @Test
    public void testClassMappingNotMappingURL() {
        servletService = new ServletService();

        String fullMappingURL = "com.example.NonExistentClass";

        Object result = servletService.classMapping(fullMappingURL);

        Assert.assertNull(result);

    }
}
