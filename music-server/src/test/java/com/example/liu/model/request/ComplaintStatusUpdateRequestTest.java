package com.example.liu.model.request;

import com.example.liu.model.domain.Complaints;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class ComplaintStatusUpdateRequestTest {

    @Test
    public void testComplaintStatusUpdateRequest_WithValidInput() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        ComplaintStatusUpdateRequest request = new ComplaintStatusUpdateRequest();
        request.setStatus(Complaints.Status.PENDING);
        request.setUpdateAt(currentTimestamp);

        assertThat(request.getStatus()).isEqualTo(Complaints.Status.PENDING);
        assertThat(request.getUpdateAt()).isEqualTo(currentTimestamp);
    }

    @Test
    public void testComplaintStatusUpdateRequest_WithNullValues() {
        ComplaintStatusUpdateRequest request = new ComplaintStatusUpdateRequest();

        assertThat(request.getStatus()).isNull();
        assertThat(request.getUpdateAt()).isNull();
    }

    @Test
    public void testComplaintStatusUpdateRequest_UpdateTimestamp() {
        Timestamp initialTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis() + 10000);

        ComplaintStatusUpdateRequest request = new ComplaintStatusUpdateRequest();
        request.setUpdateAt(initialTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(initialTimestamp);

        request.setUpdateAt(updatedTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(updatedTimestamp);
    }
}
