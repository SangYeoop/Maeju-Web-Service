package com.foodwebservice.preference;

import com.foodwebservice.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientPreferenceRepository extends JpaRepository<IngredientPreference, Long> {
    Optional<IngredientPreference> findByAccount(Account account);
}
