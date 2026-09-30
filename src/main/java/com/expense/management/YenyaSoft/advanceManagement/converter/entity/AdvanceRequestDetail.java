package com.expense.management.YenyaSoft.advanceManagement.converter.entity;

import com.expense.management.YenyaSoft.advanceManagement.converter.dto.ExpenseCategoryDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "advance_request_detail")
public class AdvanceRequestDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "expense_category", columnDefinition = "json")
    private ExpenseCategoryDto expenseCategory;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advance_request_id", nullable = false)
    private AdvanceRequest advanceRequest;
}