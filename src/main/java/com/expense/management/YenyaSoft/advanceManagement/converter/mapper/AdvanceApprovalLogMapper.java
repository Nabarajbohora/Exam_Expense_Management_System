package com.expense.management.YenyaSoft.advanceManagement.converter.mapper;
import com.expense.management.YenyaSoft.approval.dto.AdvanceApprovalLogDto;
import com.expense.management.YenyaSoft.approval.entity.AdvanceApprovalLog;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AdvanceApprovalLogMapper {

    public AdvanceApprovalLogDto toDto(AdvanceApprovalLog entity) {
        if (entity == null) {
            return null;
        }
        return AdvanceApprovalLogDto.builder()
                .id(entity.getId())
                .advanceRequest(entity.getAdvanceRequest())
                .section(entity.getSection())
                .quotation(entity.getQuotation())
                .status(entity.getStatus())
                .build();
    }
    public List<AdvanceApprovalLogDto> toDtoList(List<AdvanceApprovalLog> entities) {
        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toDto)
                .toList();
    }
    public AdvanceApprovalLog toEntity(AdvanceApprovalLogDto dto, AdvanceRequest request) {
        if (dto == null) {
            return null;
        }
        return AdvanceApprovalLog.builder()
                .section(dto.getSection())
                .quotation(dto.getQuotation())
                .status(dto.getStatus())
                .advanceRequest(request)
                .build();
    }
    public void updateEntityFromDto(AdvanceApprovalLogDto dto, AdvanceApprovalLog entity, AdvanceRequest request) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setSection(dto.getSection());
        entity.setQuotation(dto.getQuotation());
        entity.setStatus(dto.getStatus());
        entity.setAdvanceRequest(request);
    }
}
