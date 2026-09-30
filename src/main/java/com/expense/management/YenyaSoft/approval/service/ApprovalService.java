package com.expense.management.YenyaSoft.approval.service;

import com.expense.management.YenyaSoft.approval.dto.ApprovalDto;
import com.expense.management.YenyaSoft.approval.entity.AdvanceApprovalLog;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.repository.AdvanceRequestRepository;
import com.expense.management.YenyaSoft.approval.repo.AdvanceApprovalLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final AdvanceRequestRepository requestRepository;
    private final AdvanceApprovalLogRepository logRepository;
    public ApprovalDto ChangeStatus(ApprovalDto approvalDto ) {
        AdvanceRequest request = requestRepository.findById(approvalDto.getAdvanceRequestId().getId())
                .orElseThrow(()-> new RuntimeException("advance request not  found"));
        request.setStatus(approvalDto.getStatus());
        requestRepository.save(request);

        AdvanceApprovalLog approvalLog = AdvanceApprovalLog.builder()
                .section(request.getSection())
                .quotation(request.getQuotation())
                .status(approvalDto.getStatus())
                .advanceRequest(request)
                .build();

        logRepository.save(approvalLog);
        return approvalDto;
    }
}

