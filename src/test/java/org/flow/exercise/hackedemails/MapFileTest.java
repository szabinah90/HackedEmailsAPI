package org.flow.exercise.hackedemails;

import org.flow.exercise.hackedemails.fileReader.MyFileReader;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MapFileTest {

    private String expected;

    private final static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    private final static MyFileReader filereader = new MyFileReader();

    @Test
    public void fileExistsNotEmpty() {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream("elasticMap.json");

        File elasticMapFile = new File(classLoader.getResource("elasticMap.json").getFile());


        assertTrue("Mapping file does not exist or not found in resources.", elasticMapFile.exists());
        assertNotEquals("Mapping file is empty",0, elasticMapFile.length());
    }

    @Before
    public void setUp() {
        expected = filereader.readFromFile(classLoader.getResource("test.json")
                        .getFile());
    }

    @After
    public void tearDown() {
        expected = "";
    }

    @Test
    public void contentTest() {

        String actual = filereader.readFromFile(classLoader.getResource("elasticMap.json").getFile());

        assertEquals("File not matches the expected", expected, actual);
    }
}
