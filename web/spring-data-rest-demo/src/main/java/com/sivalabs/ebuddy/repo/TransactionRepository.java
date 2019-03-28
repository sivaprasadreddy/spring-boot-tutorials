package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("!isAnonymous() and isAuthenticated()")
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Override
    @Query("select t from Transaction t where t.createdBy.email=?#{principal.username}")
    Page<Transaction> findAll(Pageable pageable);
}
