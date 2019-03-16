package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Account;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByPhoneNumber(Long phoneNumber);

    Page<Account> findByCreatedTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable page);
}
