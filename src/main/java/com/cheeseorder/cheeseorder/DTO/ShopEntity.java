package com.cheeseorder.cheeseorder.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Shop")
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shopId;

    @Column
    private String shopName;

    @Column
    private String shopPhone;

    @Column
    private String address;




}
