package com.expense.management.YenyaSoft.advanceManagement.converter.entity;

import com.expense.management.YenyaSoft.advanceManagement.converter.enums.AdvanceStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "advance_request")
public class AdvanceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "advance_request_id")
    private Long id;

    @Column(name = "quotation", nullable = false)
    private String quotation;

    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "approve_amount", nullable = false)
    private BigDecimal approveAmount;

    @Column(name = "fiscal_year", nullable = false)
    private String fiscalYear;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdvanceStatus status;

    @OneToMany(
            mappedBy = "advanceRequest",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<AdvanceRequestDetail> details= new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
