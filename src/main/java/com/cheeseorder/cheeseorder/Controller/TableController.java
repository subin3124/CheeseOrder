package com.cheeseorder.cheeseorder.Controller;

import com.cheeseorder.cheeseorder.DTO.AdminMainResponse;
import com.cheeseorder.cheeseorder.DTO.MessageResponse;
import com.cheeseorder.cheeseorder.DTO.TableSize;
import com.cheeseorder.cheeseorder.Entity.TableEntity;
import com.cheeseorder.cheeseorder.Service.ShopService;
import com.cheeseorder.cheeseorder.Service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class TableController {

    @Autowired
    TableService tableService;

    @Autowired
    ShopService shopService;
    @GetMapping("/Admin/{ShopId}/{floor}")
    private AdminMainResponse MainView(@PathVariable("ShopId") long shopId,@PathVariable("floor") int floor) {
        AdminMainResponse adminMainResponse = new AdminMainResponse();
        adminMainResponse.setTable(tableService.GetTablesWithFloorAndShopId(shopId,floor));
        adminMainResponse.setShop(shopService.getShopInfo(shopId));
        return adminMainResponse;
    }
    @PostMapping("/Admin/Create/Table")
    private MessageResponse CreateTable(@PathVariable("TableId") String tableId, @RequestBody TableEntity table) {
       return tableService.createTable(table);
    }
    @PostMapping("/Admin/Move/{TableId})")
    private MessageResponse MoveTable(@PathVariable("TableId") String tableId ,@RequestBody TableSize tableSize) {
       return tableService.setTablePosition(tableId,tableSize);
    }
}