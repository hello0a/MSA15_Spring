package com.aloha.product_rest.mapper;

import java.util.List;

public interface BaseMapper<E> {
    List<E> list();
    E select (Long no);
    E selectById (String id);
    int insert(E entity);
    int update(E entity);
    int updateById(E entity);
    int delete(Long no);
    int deleteById(String id);
}
