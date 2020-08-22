package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SituationPreferenceRepository extends JpaRepository<SituationPreference, Long> {
    Optional<SituationPreference> findByAccount(Account account);
}
