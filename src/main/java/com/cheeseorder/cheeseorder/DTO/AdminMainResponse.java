package com.cheeseorder.cheeseorder.DTO;


import com.cheeseorder.cheeseorder.Entity.ShopEntity;
import com.cheeseorder.cheeseorder.Entity.TableEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AdminMainResponse {
    private List<TableEntity> table;
    private ShopEntity shop;
}
