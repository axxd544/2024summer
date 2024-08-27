package com.example.socialservice.model.request;

import com.example.socialservice.model.domain.Appeals;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 544
 * @Description:
 * @date 2024/5/30 11:50
 */
@Data
public class AppealStatusUpdateRequest {

    private Appeals.Status status;

    private Timestamp updateAt;
}
