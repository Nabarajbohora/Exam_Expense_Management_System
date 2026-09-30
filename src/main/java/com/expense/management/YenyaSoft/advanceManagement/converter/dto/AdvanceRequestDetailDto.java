package com.expense.management.YenyaSoft.advanceManagement.converter.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvanceRequestDetailDto {
    @Valid
    @NotNull
    private ExpenseCategoryDto expenseCategory;
}
