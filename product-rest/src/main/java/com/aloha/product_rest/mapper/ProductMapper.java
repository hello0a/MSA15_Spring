package com.aloha.product_rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.aloha.product_rest.dto.Product;

@Mapper
public interface ProductMapper extends BaseMapper<Product>{
   // BaseMapper 기능 상속 받음
   // 타입 매개변수 E -> Product 
}
