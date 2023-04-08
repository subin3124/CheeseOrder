package com.cheeseorder.cheeseorder.Repository;

import com.cheeseorder.cheeseorder.DTO.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,String> {
    List<TableEntity> findTableEntitiesByShop(String shop);

}
