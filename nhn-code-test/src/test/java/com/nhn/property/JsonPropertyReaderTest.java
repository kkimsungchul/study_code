package com.nhn.property;

import com.nhn.main.ServletService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class JsonPropertyReaderTest {

    private static JsonPropertyReader jsonPropertyReader;

    @BeforeClass
    public static void setup() {
        jsonPropertyReader = new JsonPropertyReader();

    }

    @Test
    public void testReadProperty() {
        ConfigVO result = jsonPropertyReader.readProperty();
        Assert.assertNotNull(result);
        //Assert.assertEquals(8080,result.getPort());
    }
}
