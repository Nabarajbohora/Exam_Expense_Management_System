package com.expense.management.YenyaSoft.approval.repo;

import com.expense.management.YenyaSoft.approval.entity.AdvanceApprovalLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvanceApprovalLogRepository extends JpaRepository<AdvanceApprovalLog, Long> {
    List<AdvanceApprovalLog > findByAdvanceRequestId( Long AdvanceRequestId);


}
