package com.expense.management.YenyaSoft.advanceManagement.converter.mapper;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDetailDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequestDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdvanceRequestMapper {

    public AdvanceRequestDto toDto(AdvanceRequest entity) {
        if (entity == null) {
            return null;
        }
        return AdvanceRequestDto.builder()
                .id(entity.getId())
                .quotation(entity.getQuotation())
                .section(entity.getSection())
                .amount(entity.getAmount())
                .fiscalYear(entity.getFiscalYear())
                .approveAmount(entity.getApproveAmount())
                .detailDto(toDetailDto(entity.getDetails()))
                .status(entity.getStatus())
                .build();
    }
    private AdvanceRequestDetailDto toDetailDto(AdvanceRequestDetail entity) {

        if (entity == null) {
            return null;
        }
        return AdvanceRequestDetailDto.builder()
                .expenseCategory(entity.getExpenseCategory())
                .build();
    }
    private List<AdvanceRequestDetailDto> toDetailDto(List<AdvanceRequestDetail> entities) {

        if (entities == null || entities.isEmpty()) {
            return new ArrayList<>();
        }
        return entities.stream()
                .map(this::toDetailDto)
                .toList();
    }
}
