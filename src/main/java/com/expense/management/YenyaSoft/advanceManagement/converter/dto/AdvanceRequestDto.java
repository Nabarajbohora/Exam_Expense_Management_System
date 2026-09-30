package com.expense.management.YenyaSoft.advanceManagement.converter.dto;

import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvanceRequestDto {
    private Long id;

    @NotBlank
    private String quotation;
    private Boolean isForSubmitting;
    private AdvanceStatus status;

    @NotBlank
    private String section;

    @NotBlank
    private String fiscalYear;
    @Valid
    @NotNull
    private List<AdvanceRequestDetailDto> detailDto;

    private BigDecimal amount;
    private BigDecimal approveAmount;

}
