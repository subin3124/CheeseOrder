package com.cheeseorder.cheeseorder.Service;

import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.Entity.TableEntity;
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

   public List<TableEntity> GetAllTablesWithShopId(long shopId) {
       try{
          List<TableEntity> entityList = tableRepository.findTableEntitiesByShopId(shopId);
          return entityList;
       }catch (DataAccessException e){
           throw new Error("data load error : "+e.getMessage());
       }
    }

   public List<TableEntity> GetTablesWithFloorAndShopId(long shopId, int floor) {
        try{
            List<TableEntity> entityList = tableRepository.findTableEntitiesByShopIdAndFloor(shopId, floor);
            return entityList;
        }catch (DataAccessException e) {
            throw new Error("data load error : "+e.getMessage());
        }
    }

   public MessageResponse setTablePosition(String tableId, TableSize size) {
        try{
            tableRepository.setTablePostion(size.getWeight(),size.getWeight(),size.getX(),size.getY(),tableId);
            return new MessageResponse(200,"success");
        }catch (DataAccessException e) {
            return new MessageResponse(400,"Data Access Error : " +e.getMessage());
        }
    }

   public void deleteTable(String tableId) {
        try{
            tableRepository.deleteById(tableId);
        }catch (DataAccessException e) {
            throw new Error("data load error : "+e.getMessage());
        }
    }


}
