package com.example.liu.model.request;

import com.example.liu.model.domain.Appeals;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class AppealStatusUpdateRequestTest {

    @Test
    public void testAppealStatusUpdateRequest_WithValidInput() {
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        AppealStatusUpdateRequest request = new AppealStatusUpdateRequest();
        request.setStatus(Appeals.Status.PENDING);
        request.setUpdateAt(currentTimestamp);

        assertThat(request.getStatus()).isEqualTo(Appeals.Status.PENDING);
        assertThat(request.getUpdateAt()).isEqualTo(currentTimestamp);
    }

    @Test
    public void testAppealStatusUpdateRequest_WithNullValues() {
        AppealStatusUpdateRequest request = new AppealStatusUpdateRequest();

        assertThat(request.getStatus()).isNull();
        assertThat(request.getUpdateAt()).isNull();
    }

    @Test
    public void testAppealStatusUpdateRequest_UpdateTimestamp() {
        Timestamp initialTimestamp = new Timestamp(System.currentTimeMillis());
        Timestamp updatedTimestamp = new Timestamp(System.currentTimeMillis() + 10000);

        AppealStatusUpdateRequest request = new AppealStatusUpdateRequest();
        request.setUpdateAt(initialTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(initialTimestamp);

        request.setUpdateAt(updatedTimestamp);

        assertThat(request.getUpdateAt()).isEqualTo(updatedTimestamp);
    }
}
