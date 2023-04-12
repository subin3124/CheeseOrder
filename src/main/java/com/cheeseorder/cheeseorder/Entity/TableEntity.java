package com.cheeseorder.cheeseorder.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "table")
public class TableEntity {

    @Id //매장Id + 층 + 테이블번호
    private String tableId;
    @Column
    private boolean isConstruct;
    @Column
    private long shopId;

    @Column
    private int floor;

    @Column
    private int sizeX;
    @Column
    private int sizeY;
    @Column private int positionX;
    @Column private int positionY;




}
