package com.example.liu.model.request;
import com.example.liu.model.domain.Complaints;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class ComplaintsRequestTest {

    @Test
    public void testComplaintsRequest_WithValidInput() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        ComplaintsRequest request = new ComplaintsRequest();
        request.setId(1);
        request.setUserId(100);
        request.setTargetType(Complaints.TargetType.SONG);
        request.setTargetId(200);
        request.setReason("Spam");
        request.setStatus(Complaints.Status.PENDING);
        request.setCreateAt(currentTimestamp);
        request.setUpdateAt(currentTimestamp);

        assertThat(request.getId()).isEqualTo(1);
        assertThat(request.getUserId()).isEqualTo(100);
        assertThat(request.getTargetType()).isEqualTo(Complaints.TargetType.SONG);
        assertThat(request.getTargetId()).isEqualTo(200);
        assertThat(request.getReason()).isEqualTo("Spam");
        assertThat(request.getStatus()).isEqualTo(Complaints.Status.PENDING);
        assertThat(request.getCreateAt()).isEqualTo(currentTimestamp);
        assertThat(request.getUpdateAt()).isEqualTo(currentTimestamp);
    }

    @Test
    public void testComplaintsRequest_WithNullValues() {
        ComplaintsRequest request = new ComplaintsRequest();

        assertThat(request.getId()).isZero();
        assertThat(request.getUserId()).isZero();
        assertThat(request.getTargetType()).isNull();
        assertThat(request.getTargetId()).isZero();
        assertThat(request.getReason()).isNull();
        assertThat(request.getStatus()).isNull();
        assertThat(request.getCreateAt()).isNull();
        assertThat(request.getUpdateAt()).isNull();
    }

    @Test
    public void testComplaintsRequest_UpdateTimestamp() {
        Timestamp initialTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis() + 10000);

        ComplaintsRequest request = new ComplaintsRequest();
        request.setUpdateAt(initialTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(initialTimestamp);

        request.setUpdateAt(updatedTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(updatedTimestamp);
    }
}
