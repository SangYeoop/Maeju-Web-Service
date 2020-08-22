package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WayPreferenceRepository extends JpaRepository<WayPreference, Long> {
    Optional<WayPreference> findByAccount(Account account);
}
