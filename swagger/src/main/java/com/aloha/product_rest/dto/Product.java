package com.aloha.product_rest.dto;

import java.util.UUID;

import org.apache.ibatis.type.Alias;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
// 아래 중요하지 않음
@EqualsAndHashCode(callSuper = false)
@Alias("Product")
public class Product extends Base{

    private String name;
    private Integer price;
    private Integer stock;
}
