package com.cheeseorder.cheeseorder.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="qrCode")
public class QrEntity {
    @Id
    String qrId;

    @Column
    String tableId;
}
