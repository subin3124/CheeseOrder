package com.cheeseorder.cheeseorder.Entity;

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
    private String shopContact;

    @Column
    private String address;

    @Column
    private String shopEmail;




}
