package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KindPreferenceRepository extends JpaRepository<KindPreference, Long> {

    Optional<KindPreference> findByAccount(Account account);
}
