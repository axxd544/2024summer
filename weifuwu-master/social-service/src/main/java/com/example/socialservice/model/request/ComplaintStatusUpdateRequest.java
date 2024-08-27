package com.example.socialservice.model.request;

import com.example.socialservice.model.domain.Complaints;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 9:04
 */
@Data
public class ComplaintStatusUpdateRequest {

    private Complaints.Status status;

    private Timestamp updateAt;
}
