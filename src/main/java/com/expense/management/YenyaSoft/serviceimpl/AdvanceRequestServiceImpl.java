package com.expense.management.YenyaSoft.serviceimpl;

import com.expense.management.YenyaSoft.advanceManagement.converter.mapper.AdvanceRequestMapper;
import com.expense.management.YenyaSoft.approval.dto.AdvanceApprovalLogDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDetailDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.AdvanceRequestDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.dto.ExpenseCategoryDto;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequestDetail;
import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import com.expense.management.YenyaSoft.advanceManagement.converter.repository.AdvanceRequestRepository;
import com.expense.management.YenyaSoft.advanceManagement.converter.service.AdvanceRequestService;
import com.expense.management.YenyaSoft.approval.service.ApprovalLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvanceRequestServiceImpl implements AdvanceRequestService {
    private final AdvanceRequestRepository advanceRequestRepository;
    private final AdvanceRequestMapper advanceRequestMapper;
    private final ApprovalLogService approvalLogService;

    @Override
    @Transactional
    public AdvanceRequestDto createAdvanceRequest(AdvanceRequestDto r) {
        AdvanceRequest advanceRequest = AdvanceRequest.builder()
                .quotation(r.getQuotation())
                .section(r.getSection())
                .fiscalYear(r.getFiscalYear())
                .amount(BigDecimal.ZERO)
                .approveAmount(BigDecimal.ZERO)
                .build();

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<AdvanceRequestDetail> details = new ArrayList<>();

        for (AdvanceRequestDetailDto detailDto : r.getDetailDto()) {
            ExpenseCategoryDto expenseCategory = detailDto.getExpenseCategory();
            BigDecimal detailAmount = calculateTotalAmount(expenseCategory);
            AdvanceRequestDetail detail = AdvanceRequestDetail.builder()
                    .expenseCategory(expenseCategory)
                    .amount(detailAmount)
                    .advanceRequest(advanceRequest)
                    .build();
            details.add(detail);
            totalAmount = totalAmount.add(detailAmount);
        }
        advanceRequest.setAmount(totalAmount);
        advanceRequest.setDetails(details);
        if (r.getIsForSubmitting().equals(Boolean.TRUE)) {
            advanceRequest.setStatus(AdvanceStatus.UNDER_REVIEW);
        } else {
            advanceRequest.setStatus(AdvanceStatus.DRAFT);
        }
        AdvanceRequest saved = advanceRequestRepository.save(advanceRequest);
        if (r.getIsForSubmitting().equals(Boolean.TRUE)) {
            approvalLogService.createApprovalLog(
                    AdvanceApprovalLogDto.builder()
                            .section(r.getSection()).quotation(r.getQuotation())
                            .status(AdvanceStatus.PENDING)
                            .advanceRequest(saved)
                            .build()
            );
        }
        return advanceRequestMapper.toDto(saved);
    }
    @Override
    @Transactional
    public AdvanceRequestDto updateAdvanceRequest(AdvanceRequestDto advanceRequestDto) {
        AdvanceRequest existingRequest =
                advanceRequestRepository.findById(advanceRequestDto.getId())
                        .orElseThrow(() -> new RuntimeException("Advance request not found with id: "
                                + advanceRequestDto.getId()));

        if (!existingRequest.getStatus().equals(AdvanceStatus.DRAFT)) {
            throw new RuntimeException("Only draft approval request can be updated");
        }
        existingRequest.setQuotation(advanceRequestDto.getQuotation());
        existingRequest.setSection(advanceRequestDto.getSection());
        existingRequest.setFiscalYear(advanceRequestDto.getFiscalYear());

        existingRequest.getDetails().clear();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (AdvanceRequestDetailDto detailDto : advanceRequestDto.getDetailDto()) {
            ExpenseCategoryDto expenseCategory = detailDto.getExpenseCategory();

            BigDecimal detailAmount = calculateTotalAmount(expenseCategory);
            AdvanceRequestDetail detail = AdvanceRequestDetail.builder()
                    .expenseCategory(expenseCategory)
                    .amount(detailAmount)
                    .advanceRequest(existingRequest)
                    .build();
            existingRequest.getDetails().add(detail);
            totalAmount = totalAmount.add(detailAmount);
        }
        existingRequest.setAmount(totalAmount);
        if (advanceRequestDto.getIsForSubmitting().equals(Boolean.TRUE)) {
            existingRequest.setStatus(AdvanceStatus.UNDER_REVIEW);
        } else {
            existingRequest.setStatus(AdvanceStatus.DRAFT);
        }
        AdvanceRequest savedRequest = advanceRequestRepository.save(existingRequest);
        if (advanceRequestDto.getIsForSubmitting().equals(Boolean.TRUE)) {
            approvalLogService.createApprovalLog(
                    AdvanceApprovalLogDto.builder()
                            .section(advanceRequestDto.getSection()).quotation(advanceRequestDto.getQuotation())
                            .status(AdvanceStatus.PENDING)
                            .advanceRequest(savedRequest)
                            .build()
            );
        }
        return advanceRequestMapper.toDto(savedRequest);
    }
    @Override
    @Transactional
    public AdvanceRequestDto findAdvanceRequestById(Long id) {
        AdvanceRequest advanceRequest = advanceRequestRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Advance request not found with id: " + id));
        return advanceRequestMapper.toDto(advanceRequest);
    }
    @Override
    @Transactional
    public void deleteAdvanceRequest(Long id) {
        AdvanceRequest advanceRequest = advanceRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Advance Request not found with id:" + id));
        advanceRequestRepository.delete(advanceRequest);
    }
    private BigDecimal calculateTotalAmount(ExpenseCategoryDto category) {
        if (category.getChildren() == null || category.getChildren().isEmpty()) {
            return category.getAmount();
        }
        BigDecimal total = BigDecimal.ZERO;
        for (ExpenseCategoryDto child : category.getChildren()) {
            total = total.add(calculateTotalAmount(child));
        }
        return total;
    }
}
