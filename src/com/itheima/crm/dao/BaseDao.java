package com.itheima.crm.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface BaseDao<T> {
    public void save(T t);
    public void delete(T t);
    public void update(T t);
    public T findById(Long id);

    public Long findCount(DetachedCriteria detachedCriteria);
    public List<T> findByPage(DetachedCriteria detachedCriteria,Integer currentPage,Integer pageSize);
    public List<T> findAll();
}
