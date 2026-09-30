package com.expense.management.YenyaSoft.advanceManagement.converter.repository;

import com.expense.management.YenyaSoft.advanceManagement.converter.entity.AdvanceRequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvanceRequestDetailRepository extends JpaRepository<AdvanceRequestDetail ,Long > {
}
