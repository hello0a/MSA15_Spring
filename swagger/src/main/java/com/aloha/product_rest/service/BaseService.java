package com.aloha.product_rest.service;

import java.util.List;

import com.aloha.product_rest.dto.Pagination;
import com.github.pagehelper.PageInfo;

public interface BaseService<E> {
    List<E> list();
    PageInfo<E> page(Pagination pagination);
    E select (Long no);
    E selectById (String id);
    boolean insert(E entity);
    boolean update(E entity);
    boolean updateById(E entity);
    boolean delete(Long no);
    boolean deleteById(String id);
}
