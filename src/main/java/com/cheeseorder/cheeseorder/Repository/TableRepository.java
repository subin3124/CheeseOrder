package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.DTO.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,String> {
    List<TableEntity> findTableEntitiesByShopId(String shopId);
    @Query(value = "update table set sizeX = :sx, sizeY = :sy, positionX = :px,positionY=:py where shopId = :shopId",nativeQuery = true)
    void setTablePostion(@Param("sx")int sx, @Param("sy")int sy, @Param("px")int px, @Param("py")int py, @Param("shopId")String shopId);

}
