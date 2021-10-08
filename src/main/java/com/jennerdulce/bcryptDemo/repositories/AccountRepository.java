package com.jennerdulce.bcryptDemo.repositories;

import com.jennerdulce.bcryptDemo.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
        Account findByUsername(String username);
}
