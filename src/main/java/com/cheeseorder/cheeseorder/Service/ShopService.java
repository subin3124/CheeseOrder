package com.cheeseorder.cheeseorder.Service;

import com.cheeseorder.cheeseorder.Entity.ShopEntity;
import com.cheeseorder.cheeseorder.Repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    public ShopEntity getShopInfo(long shopId) {
        try{
           return shopRepository.findShopEntityByShopId(shopId);
        }catch (DataAccessException e){
            throw new Error("data load error : "+e.getMessage());
        }
    }
}
