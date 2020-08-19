package com.foodwebservice.diet;

import com.foodwebservice.account.Account;
import com.foodwebservice.account.AccountService;
import com.foodwebservice.diet.form.QuestionForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class DietService {
    private final DietRepository dietRepository;
    private final AccountService accountService;

    @Transactional
    public Diet makeDiet(Account account, QuestionForm questionForm){
        account = accountService.findById(account.getId());
        account.setExistDietType(true);
        Diet diet = dietRepository.findByAccount(account).orElse(new Diet());

        diet.setPoint(questionForm);
        diet.setAccount(account);
        diet.setDietType();

        return dietRepository.save(diet);
    }


}
