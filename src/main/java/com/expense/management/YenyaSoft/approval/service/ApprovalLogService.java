package com.expense.management.YenyaSoft.approval.service;


import com.expense.management.YenyaSoft.approval.dto.AdvanceApprovalLogDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApprovalLogService {
    AdvanceApprovalLogDto createApprovalLog(AdvanceApprovalLogDto approvalLogDto);

    List<AdvanceApprovalLogDto> findByAdvanceRequestId(Long advanceRequestId);

    AdvanceApprovalLogDto findApprovalLogById(Long id);
}
