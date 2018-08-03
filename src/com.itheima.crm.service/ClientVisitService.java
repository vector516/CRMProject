package com.itheima.crm.service;

import com.itheima.crm.domain.ClientVisit;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface ClientVisitService {
    void save(ClientVisit model);

    PageBean<ClientVisit> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    void delete(ClientVisit model);
}
