package com.example.liu.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RTest {

    @Test
    public void testSuccessWithMessage() {
        R response = R.success("Operation successful");

        assertEquals(200, response.getCode());
        assertEquals("Operation successful", response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals("success", response.getType());
        assertNull(response.getData());
    }

    @Test
    public void testSuccessWithMessageAndData() {
        Object data = new Object();
        R response = R.success("Operation successful", data);

        assertEquals(200, response.getCode());
        assertEquals("Operation successful", response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals("success", response.getType());
        assertEquals(data, response.getData());
    }

    @Test
    public void testWarning() {
        R response = R.warning("This is a warning");

        assertEquals(200, response.getCode());
        assertEquals("This is a warning", response.getMessage());
        assertFalse(response.getSuccess());
        assertEquals("warning", response.getType());
        assertNull(response.getData());
    }

    @Test
    public void testError() {
        R response = R.error("An error occurred");

        assertEquals(200, response.getCode());
        assertEquals("An error occurred", response.getMessage());
        assertFalse(response.getSuccess());
        assertEquals("error", response.getType());
        assertNull(response.getData());
    }

    @Test
    public void testFatal() {
        R response = R.fatal("A fatal error occurred");

        assertEquals(500, response.getCode());
        assertEquals("A fatal error occurred", response.getMessage());
        assertFalse(response.getSuccess());
        assertEquals("error", response.getType());
        assertNull(response.getData());
    }
}
