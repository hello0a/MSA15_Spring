package com.aloha.product_rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.product_rest.dto.Pagination;
import com.aloha.product_rest.dto.Product;
import com.aloha.product_rest.service.ProductService;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService productService;

    // sp-crud 사용하자!
    // REST 기반으로 코드 작성해줌
    @GetMapping()
    public ResponseEntity<?> getAll(Pagination pagination) {
        try {
            PageInfo<Product> gageInfo = productService.page(pagination);
            return new ResponseEntity<>(gageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") String id) {
        try {
            Product product = productService.selectById(id);
            if (product == null) {
                return new ResponseEntity<>("FAIL", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Product product) {
        try {
            boolean result = productService.insert(product);
            if (!result) {
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } catch (Exception e) {
             e.printStackTrace();
            return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping()
    public ResponseEntity<?> update(@RequestBody Product product) {
        try {
            boolean result = productService.updateById(product);
            if (!result) {
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
            }        
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
            return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
        }
}    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> destroy(@PathVariable("id") String id) {
        try {
            boolean result = productService.deleteById(id);
            if (!result) {
                return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
             e.printStackTrace();
            return new ResponseEntity<>("EXCEPTION", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
