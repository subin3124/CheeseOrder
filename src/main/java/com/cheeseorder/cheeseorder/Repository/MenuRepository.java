package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.Entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity,Long> {
    List<MenuEntity> findMenuEntitiesByShopId(long shopId);
}
