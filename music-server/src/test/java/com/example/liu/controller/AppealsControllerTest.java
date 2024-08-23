package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.AppealStatusUpdateRequest;
import com.example.liu.model.request.AppealsRequest;
import com.example.liu.service.AppealsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AppealsControllerTest {

    @Mock
    private AppealsService appealsService;

    @InjectMocks
    private AppealsController appealsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitAppeals_Success() {
        AppealsRequest appealsRequest = new AppealsRequest();
        R expectedResponse = R.success("Appeal submitted successfully");
        when(appealsService.submitAppeals(any(AppealsRequest.class))).thenReturn(expectedResponse);

        R response = appealsController.submitAppeals(appealsRequest);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testSubmitAppeals_Failure() {
        AppealsRequest appealsRequest = new AppealsRequest();
        R expectedResponse = R.error("Failed to submit appeal");
        when(appealsService.submitAppeals(any(AppealsRequest.class))).thenReturn(expectedResponse);

        R response = appealsController.submitAppeals(appealsRequest);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testViewAllAppeals_Success() {
        R expectedResponse = R.success("Fetched all appeals successfully");
        when(appealsService.viewAllAppeals()).thenReturn(expectedResponse);

        R response = appealsController.viewAllAppeals();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testViewAllAppeals_Failure() {
        R expectedResponse = R.error("Failed to fetch appeals");
        when(appealsService.viewAllAppeals()).thenReturn(expectedResponse);

        R response = appealsController.viewAllAppeals();

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testUpdateAppealStatus_Success() {
        AppealStatusUpdateRequest appealStatusUpdateRequest = new AppealStatusUpdateRequest();
        R expectedResponse = R.success("Appeal status updated successfully");
        when(appealsService.updateAppealStatus(any(Integer.class), any(AppealStatusUpdateRequest.class)))
                .thenReturn(expectedResponse);

        R response = appealsController.updateAppealStatus(1, appealStatusUpdateRequest);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void testUpdateAppealStatus_Failure() {
        AppealStatusUpdateRequest appealStatusUpdateRequest = new AppealStatusUpdateRequest();
        R expectedResponse = R.error("Failed to update appeal status");
        when(appealsService.updateAppealStatus(any(Integer.class), any(AppealStatusUpdateRequest.class)))
                .thenReturn(expectedResponse);

        R response = appealsController.updateAppealStatus(1, appealStatusUpdateRequest);

        assertThat(response).isEqualTo(expectedResponse);
    }
}
