package com.example.liu.model.request;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AdminRequestTest {

    @Test
    public void testAdminRequest_WithNullValues() {
        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setId(null);
        adminRequest.setUsername(null);
        adminRequest.setPassword(null);

        assertThat(adminRequest.getId()).isNull();
        assertThat(adminRequest.getUsername()).isNull();
        assertThat(adminRequest.getPassword()).isNull();
    }

    @Test
    public void testAdminRequest_WithEmptyValues() {

        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setId(0);
        adminRequest.setUsername("");
        adminRequest.setPassword("");

        assertThat(adminRequest.getId()).isEqualTo(0);
        assertThat(adminRequest.getUsername()).isEqualTo("");
        assertThat(adminRequest.getPassword()).isEqualTo("");
    }

    @Test
    public void testAdminRequest_WithNegativeId() {

        AdminRequest adminRequest = new AdminRequest();
        adminRequest.setId(-1);
        adminRequest.setUsername("adminUser");
        adminRequest.setPassword("password");
        assertThat(adminRequest.getId()).isEqualTo(-1);
        assertThat(adminRequest.getUsername()).isEqualTo("adminUser");
        assertThat(adminRequest.getPassword()).isEqualTo("password");
    }
}
