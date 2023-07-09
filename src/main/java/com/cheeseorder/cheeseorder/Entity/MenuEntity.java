package com.cheeseorder.cheeseorder.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MenuEntity {

    @Id
    private long menuId;

    @Column
    private long shopId;

    @Column
    private boolean isVisible;

    @Column
    private String menuName;

    @Column
    private String descriptionMenu;

    @Column
    private String imgUrl;

    @Column
    private int price;
}
