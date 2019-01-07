package com.daduo.api.tiktokapi.repository;

import com.daduo.api.tiktokapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findOneByPhoneNumber(Long phoneNumber);

    Account findOneByPhoneNumberAndPassword(Long phoneNumber, String password);
}
