package com.expense.management.YenyaSoft.approval.controller;


import com.expense.management.YenyaSoft.approval.dto.AdvanceApprovalLogDto;
import com.expense.management.YenyaSoft.approval.dto.ApprovalDto;
import com.expense.management.YenyaSoft.approval.service.ApprovalLogService;
import com.expense.management.YenyaSoft.approval.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/advance/approvalLog")
@RequiredArgsConstructor
public class ApprovalLogController {

    private final ApprovalLogService approvalLogService;
    @PostMapping
    public ResponseEntity<AdvanceApprovalLogDto> createApprovalLog(@RequestBody AdvanceApprovalLogDto approvalLogDto) {
        AdvanceApprovalLogDto createdLog = approvalLogService.createApprovalLog(approvalLogDto);
        return ResponseEntity.ok(createdLog);
    }
    @GetMapping("/request/{advanceRequestId}")
    public ResponseEntity<List<AdvanceApprovalLogDto>> getLogsByAdvanceRequestId(@PathVariable Long advanceRequestId) {
        List<AdvanceApprovalLogDto> logs = approvalLogService.findByAdvanceRequestId(advanceRequestId);
        return ResponseEntity.ok(logs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AdvanceApprovalLogDto> getApprovalLogById(@PathVariable Long id) {
        AdvanceApprovalLogDto log = approvalLogService.findApprovalLogById(id);
        return ResponseEntity.ok(log);
    }


}