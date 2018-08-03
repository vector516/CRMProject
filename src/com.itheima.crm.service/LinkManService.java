package com.itheima.crm.service;

import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

public interface LinkManService {
    void save(LinkMan linkMan);

    PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

    LinkMan findById(Long lkm_id);

    void delete(LinkMan linkMan);

    void update(LinkMan linkMan);
}
