package com.javarush.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CityTest {

    @Test
    public void testGetNameWhenNull() {
        City city = new City();
        city.setName(null);
        assertEquals(null, city.getName());
    }

    @Test
    public void testGetNameWhenEmpty() {
        City city = new City();
        city.setName("");
        assertEquals("", city.getName());
    }

    @Test
    public void testGetNameWhenNotEmpty() {
        City city = new City();
        city.setName("New York");
        assertEquals("New York", city.getName());
    }
}