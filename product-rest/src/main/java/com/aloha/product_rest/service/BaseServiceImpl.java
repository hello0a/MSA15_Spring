package com.aloha.product_rest.service;

import java.util.List;

import com.aloha.product_rest.dto.Base;
import com.aloha.product_rest.dto.Pagination;
import com.aloha.product_rest.mapper.BaseMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
// E : dto 타입 / M : mapper 타입
public class BaseServiceImpl<E extends Base, M extends BaseMapper<E>> implements BaseService<E>{
    
    private final M mapper;

    @Override
    public List<E> list() {
        return mapper.list();
    }

    @Override
    public PageInfo<E> page(Pagination pagination) {
        int page = (int) pagination.getPage();
        int size = (int) pagination.getSize();
        int count = (int) pagination.getCount();
        PageHelper.startPage(page, size);
        List<E> list = mapper.list();
        PageInfo<E> pageInfo = new PageInfo<>(list, count);
        return pageInfo;
    }

    @Override
    public E select(Long no) {
        E entity = mapper.select(no);
        return entity;
    }

    @Override
    public E selectById(String id) {
        E entity = mapper.selectById(id);
        return entity;
    }

    @Override
    public boolean insert(E entity) {
        int result = mapper.insert(entity);
        return result > 0;
    }

    @Override
    public boolean update(E entity) {
        int result = mapper.update(entity);
        return result > 0;
    }

    @Override
    public boolean updateById(E entity) {
        int result = mapper.updateById(entity);
        return result > 0;
    }

    @Override
    public boolean delete(Long no) {
        int result = mapper.delete(no);
        return result > 0;
    }

    @Override
    public boolean deleteById(String id) {
        int result = mapper.deleteById(id);
        return result > 0;
    }
}
