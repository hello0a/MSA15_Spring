package com.aloha.product_rest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aloha.product_rest.dto.Product;
import com.aloha.product_rest.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product, ProductMapper>
                                implements ProductService{

    public ProductServiceImpl(ProductMapper mapper) {
        super(mapper);
        // 부모 생성자 Mapper
        // 아직 타입이 정해지지 않아서,,,? 라는데 뭔말이야
        }
    

}
