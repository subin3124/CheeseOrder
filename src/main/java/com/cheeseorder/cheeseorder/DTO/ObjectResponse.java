package com.cheeseorder.cheeseorder.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObjectResponse {
    private int code;
    private Object data;
}
