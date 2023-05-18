package com.cheeseorder.cheeseorder.Controller;

import com.cheeseorder.cheeseorder.DTO.AdminMainResponse;
import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.DTO.TableSize;
import com.cheeseorder.cheeseorder.Entity.TableEntity;
import com.cheeseorder.cheeseorder.Service.ShopService;
import com.cheeseorder.cheeseorder.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class TableController {

    @Autowired
    TableService tableService;

    @Autowired
    ShopService shopService;
    @GetMapping("/Admin/{ShopId}/{floor}")
    private List<TableEntity> MainView(@PathVariable("ShopId") long shopId, @PathVariable("floor") int floor) {
        AdminMainResponse adminMainResponse = new AdminMainResponse();
        System.out.println("get data : "+shopId);
        return tableService.GetTablesWithFloorAndShopId(shopId,floor);
    }
    @PostMapping("/Admin/Create/{TableId}")
    private MessageResponse CreateTable(@PathVariable("TableId") String tableId, @RequestBody TableEntity table) {
       return tableService.createTable(table);
    }
    @PostMapping("/Admin/Move/{TableId})")
    private MessageResponse MoveTable(@PathVariable("TableId") String tableId ,@RequestBody TableSize tableSize) {
       return tableService.setTablePosition(tableId,tableSize);
    }
}
