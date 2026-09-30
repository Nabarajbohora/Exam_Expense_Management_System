package com.expense.management.YenyaSoft.approval.dto;

import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDto {
    private AdvanceRequest advanceRequestId;
    private AdvanceStatus status;
    private String remarks;


}
