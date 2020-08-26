package com.foodwebservice.shop;

import com.foodwebservice.account.Account;
import com.foodwebservice.food.Food;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ShopService {
    private final ShopRepository shopRepository;

    public void addShop(Account account, Food food) {
        Shop shop = new Shop();

        shop.setAccount(account);
        account.getShops().add(shop);

        shop.setFood(food);
        shopRepository.save(shop);
    }


    public void removeShop(Account account, Food food){
        Shop shop = shopRepository.findByAccountAndFood(account, food).orElseThrow(IllegalArgumentException::new);
        shopRepository.delete(shop);
    }

    public List<Shop> findWithFoodAllByAccount(Account user) {
        return shopRepository.findWithFoodAllByAccount(user);
    }
}
