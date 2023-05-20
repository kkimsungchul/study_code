package com.nhn.http;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HttpRequestTest {
    private HttpRequest httpRequest;

    @Before
    public void setup() {
        httpRequest = new HttpRequest(null,null);
    }

    @Test
    public void testExtractQueryParams() {
        //String queryString = "seq=100&searchname=%EA%B9%80%EC%84%B1%EC%B2%A0";
        String queryString = "seq=100&searchName=김성철";

        httpRequest.extractQueryParams(queryString);

        Assert.assertEquals("100", httpRequest.getParameter("seq"));
        Assert.assertEquals("김성철", httpRequest.getParameter("searchName"));
    }

    @Test
    public void testExtractQueryParamsNotQueryString() {
        String queryString = "NotQueryString";

        httpRequest.extractQueryParams(queryString);

        Assert.assertTrue(httpRequest.getParameters().isEmpty());

    }

    @Test
    public void testGetAllParameter() {
        httpRequest.setParameter("searchKey", "이름");
        httpRequest.setParameter("searchValue", "값");

        String result = httpRequest.getAllParameter();

        String expected = "Query String <br>Key: searchKey, Value: 이름<br>Key: searchValue, Value: 값<br>";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetAllParameterNull() {
        String result = httpRequest.getAllParameter();

        Assert.assertEquals("", result);
    }
}
