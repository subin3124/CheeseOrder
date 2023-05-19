package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.Entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,String> {

    List<TableEntity> findTableEntitiesByShopId(long shopId);
    @Query(value = "update table_entity set sizex = ?1, sizey = ?2, positionx = ?3,positiony=?4 where table_id = ?5",nativeQuery = true)
    void setTablePosition(int sx, int sy, int px, int py, String tableId);

    TableEntity findTableEntityByTableId(String tableId);

    List<TableEntity> findTableEntitiesByShopIdAndFloor(long ShopId, int floor);
}
