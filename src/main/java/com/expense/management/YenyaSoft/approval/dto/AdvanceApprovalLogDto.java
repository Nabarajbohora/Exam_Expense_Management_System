package com.expense.management.YenyaSoft.approval.dto;

import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvanceApprovalLogDto {
    private Long id;
    private String section;
    private String quotation;
    private AdvanceStatus status;
    private AdvanceRequest advanceRequest;
}
