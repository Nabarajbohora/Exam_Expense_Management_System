package com.expense.management.YenyaSoft.approval.entity;

import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequest;
import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "advance_approval_log")
public class AdvanceApprovalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "quotation", nullable = false)
    private String quotation;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvanceStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advance_request_id", nullable = false)
    private AdvanceRequest advanceRequest;

}
