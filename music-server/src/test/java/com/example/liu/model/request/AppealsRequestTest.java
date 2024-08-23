package com.example.liu.model.request;

import com.example.liu.model.domain.Appeals;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class AppealsRequestTest {

    @Test
    public void testAppealsRequest_WithValidInput() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        AppealsRequest appealsRequest = new AppealsRequest();
        appealsRequest.setId(1);
        appealsRequest.setComplaintId(1001);
        appealsRequest.setUserId(501);
        appealsRequest.setReason("The reason for the appeal.");
        appealsRequest.setStatus(Appeals.Status.PENDING);
        appealsRequest.setCreateAt(currentTimestamp);
        appealsRequest.setUpdateAt(currentTimestamp);

        assertThat(appealsRequest.getId()).isEqualTo(1);
        assertThat(appealsRequest.getComplaintId()).isEqualTo(1001);
        assertThat(appealsRequest.getUserId()).isEqualTo(501);
        assertThat(appealsRequest.getReason()).isEqualTo("The reason for the appeal.");
        assertThat(appealsRequest.getStatus()).isEqualTo(Appeals.Status.PENDING);
        assertThat(appealsRequest.getCreateAt()).isEqualTo(currentTimestamp);
        assertThat(appealsRequest.getUpdateAt()).isEqualTo(currentTimestamp);
    }

    @Test
    public void testAppealsRequest_WithNullValues() {
        AppealsRequest appealsRequest = new AppealsRequest();

        assertThat(appealsRequest.getId()).isEqualTo(0);
        assertThat(appealsRequest.getComplaintId()).isEqualTo(0);
        assertThat(appealsRequest.getUserId()).isEqualTo(0);
        assertThat(appealsRequest.getReason()).isNull();
        assertThat(appealsRequest.getStatus()).isNull();
        assertThat(appealsRequest.getCreateAt()).isNull();
        assertThat(appealsRequest.getUpdateAt()).isNull();
    }

    @Test
    public void testAppealsRequest_StatusEnum() {
        AppealsRequest appealsRequest = new AppealsRequest();
        appealsRequest.setStatus(Appeals.Status.PENDING);

        assertThat(appealsRequest.getStatus()).isEqualTo(Appeals.Status.PENDING);

        appealsRequest.setStatus(Appeals.Status.REVIEWED);

        assertThat(appealsRequest.getStatus()).isEqualTo(Appeals.Status.REVIEWED);
    }

    @Test
    public void testAppealsRequest_UpdateTimestamps() {
        Timestamp initialTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis() + 10000);

        AppealsRequest appealsRequest = new AppealsRequest();
        appealsRequest.setCreateAt(initialTimestamp);
        appealsRequest.setUpdateAt(initialTimestamp);

        assertThat(appealsRequest.getCreateAt()).isEqualTo(initialTimestamp);
        assertThat(appealsRequest.getUpdateAt()).isEqualTo(initialTimestamp);

        appealsRequest.setUpdateAt(updatedTimestamp);

        assertThat(appealsRequest.getUpdateAt()).isEqualTo(updatedTimestamp);
    }
}
