package com.expense.management.YenyaSoft.approval.controller;


import com.expense.management.YenyaSoft.approval.dto.ApprovalDto;
import com.expense.management.YenyaSoft.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/approvals")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;
    /**
     * author nabraj bohora .
     */
    @PutMapping("/change-status")
    public ResponseEntity<ApprovalDto> changeStatus(@RequestBody ApprovalDto approvalDto) {
        ApprovalDto updatedApproval = approvalService.ChangeStatus(approvalDto);
        return ResponseEntity.ok(updatedApproval);
    }

}
