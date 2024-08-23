package com.example.liu.controller;

import com.example.liu.common.R;
import com.example.liu.model.request.ComplaintStatusUpdateRequest;
import com.example.liu.model.request.ComplaintsRequest;
import com.example.liu.service.ComplaintsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ComplaintsControllerTest {

    @InjectMocks
    private ComplaintsController complaintsController;

    @Mock
    private ComplaintsService complaintsService;

    public ComplaintsControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSubmitComplaints_Success() {
        ComplaintsRequest complaintsRequest = new ComplaintsRequest();
        complaintsRequest.setUserId(1);
        complaintsRequest.setReason("Test complaint");

        when(complaintsService.submitComplaints(complaintsRequest)).thenReturn(R.success("Complaint submitted"));

        R response = complaintsController.submitComplaints(complaintsRequest);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Complaint submitted");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testSubmitComplaints_Failure() {
        ComplaintsRequest complaintsRequest = new ComplaintsRequest();
        complaintsRequest.setUserId(1);
        complaintsRequest.setReason("");

        when(complaintsService.submitComplaints(complaintsRequest)).thenReturn(R.error("Complaint submission failed"));

        R response = complaintsController.submitComplaints(complaintsRequest);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Complaint submission failed");
        assertThat(response.getSuccess()).isFalse();
    }

    @Test
    public void testViewAllComplaints() {
        when(complaintsService.viewAllComplaints()).thenReturn(R.success("Complaints retrieved", "data"));

        R response = complaintsController.viewAllComplaints();

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Complaints retrieved");
        assertThat(response.getData()).isEqualTo("data");
    }

    @Test
    public void testGetComplaintsById_Success() {
        int complaintId = 1;
        when(complaintsService.getComplaintsById(complaintId)).thenReturn(R.success("Complaint found", "complaintData"));

        R response = complaintsController.getComplaintsById(complaintId);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Complaint found");
        assertThat(response.getData()).isEqualTo("complaintData");
    }

    @Test
    public void testUpdateComplaintStatus_Success() {
        ComplaintStatusUpdateRequest updateRequest = new ComplaintStatusUpdateRequest();


        when(complaintsService.updateComplaintStatus(1, updateRequest)).thenReturn(R.success("Status updated"));

        R response = complaintsController.updateComplaintStatus(1, updateRequest);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Status updated");
        assertThat(response.getSuccess()).isTrue();
    }

    @Test
    public void testUpdateComplaintStatus_Failure() {
        ComplaintStatusUpdateRequest updateRequest = new ComplaintStatusUpdateRequest();
 
        when(complaintsService.updateComplaintStatus(1, updateRequest)).thenReturn(R.error("Status update failed"));

        R response = complaintsController.updateComplaintStatus(1, updateRequest);

        assertThat(response.getCode()).isEqualTo(200);
        assertThat(response.getMessage()).isEqualTo("Status update failed");
        assertThat(response.getSuccess()).isFalse();
    }
}
