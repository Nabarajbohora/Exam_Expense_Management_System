package com.expense.management.YenyaSoft.advanceManagement.converter.service;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AdvanceRequestService {
    AdvanceRequestDto createAdvanceRequest(AdvanceRequestDto advanceRequestDto);

    AdvanceRequestDto updateAdvanceRequest(AdvanceRequestDto advanceRequestDto);

    AdvanceRequestDto findAdvanceRequestById(Long id);

    void deleteAdvanceRequest(Long id);


}
