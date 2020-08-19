package com.foodwebservice.diet;

import com.foodwebservice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DietRepository extends JpaRepository<Diet, Long> {
    Optional<Diet> findByAccount(Account account);
}
