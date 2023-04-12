package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.Entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<ShopEntity,Long> {
    ShopEntity findShopEntityByShopId(long shopId);
}
