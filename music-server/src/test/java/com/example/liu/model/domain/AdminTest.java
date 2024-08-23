package com.example.liu.model.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdminTest {

    @Test
    public void testAdmin() {
        Admin admin = new Admin();
        
        assertNull(admin.getId());
        assertNull(admin.getName());
        assertNull(admin.getPassword());

        admin.setId(1);
        admin.setName("admin1234");
        admin.setPassword("password");
        assertEquals(1, admin.getId());
        assertEquals("admin1234", admin.getName());
        assertEquals("password", admin.getPassword());

        admin.setId(-1);
        admin.setName("admin");
        admin.setPassword("password");
        assertEquals(-1, admin.getId());
        assertEquals("admin", admin.getName());
        assertEquals("password", admin.getPassword());
    }

    @Test
    public void testToString() {
        Admin admin = new Admin();
        admin.setId(1);
        admin.setName("admin");
        admin.setPassword("password");
        assertTrue(admin.toString().contains("id=1"));
        assertTrue(admin.toString().contains("name=admin"));
        assertTrue(admin.toString().contains("password=password"));
    }

}
