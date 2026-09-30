package com.expense.management.YenyaSoft.approval.service;

import com.expense.management.YenyaSoft.advanceManagement.converter.mapper.AdvanceApprovalLogMapper;
import com.expense.management.YenyaSoft.approval.dto.AdvanceApprovalLogDto;
import com.expense.management.YenyaSoft.approval.entity.AdvanceApprovalLog;
import com.expense.management.YenyaSoft.approval.repo.AdvanceApprovalLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvanceApprovalLogServiceImpl implements ApprovalLogService {
    private final AdvanceApprovalLogRepository logRepository;
    private final AdvanceApprovalLogMapper logMapper;

    @Override
    @Transactional
    public AdvanceApprovalLogDto createApprovalLog(AdvanceApprovalLogDto approvalLogDto) {
        AdvanceApprovalLog advanceApprovalLog = logMapper.toEntity(approvalLogDto, approvalLogDto.getAdvanceRequest());
        AdvanceApprovalLog saved = logRepository.save(advanceApprovalLog);
        return logMapper.toDto(saved);
    }

    @Override
    public List<AdvanceApprovalLogDto> findByAdvanceRequestId(Long advanceRequestId) {
        List<AdvanceApprovalLog> logs = logRepository.findByAdvanceRequestId(advanceRequestId);
        return logMapper.toDtoList(logs);
    }

    @Override
    public AdvanceApprovalLogDto findApprovalLogById(Long id) {
        AdvanceApprovalLog log = logRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval Log not found with id: " + id));
        return logMapper.toDto(log);

    }
}

