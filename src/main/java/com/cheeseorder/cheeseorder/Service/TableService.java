package com.cheeseorder.cheeseorder.Service;

import com.cheeseorder.cheeseorder.DTO.TableEntity;
import com.cheeseorder.cheeseorder.DTO.TableSize;
import com.cheeseorder.cheeseorder.Repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService {
    @Autowired
    TableRepository tableRepository;

    List<TableEntity> GetAllEntities(String shopId) {
       try{
          List<TableEntity> entityList = tableRepository.findTableEntitiesByShopId(shopId);
          return entityList;
       }catch (DataAccessException e){
           throw new Error("data load error : "+e.getMessage());
       }
    }

    void setTablePosition(String tableId, TableSize size) {
        try{
            tableRepository.setTablePostion(size.getWeight(),size.getWeight(),size.getX(),size.getY(),tableId);
        }catch (DataAccessException e) {
            throw new Error("data load error : " + e.getMessage());
        }
    }


}
