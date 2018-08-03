package com.itheima.crm.service.impl;

import com.itheima.crm.dao.ClientVisitDao;
import com.itheima.crm.domain.ClientVisit;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ClientVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class ClientVisitServiceImpl implements ClientVisitService {
    @Autowired
    private ClientVisitDao clientVisitDao;

    @Override
    public void save(ClientVisit model) {
        clientVisitDao.save(model);
    }

    @Override
    public PageBean<ClientVisit> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        PageBean<ClientVisit> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总数据条数
        Long totalCount = clientVisitDao.findCount(detachedCriteria);

        int totalCount2 = totalCount.intValue();
        pageBean.setTotalCount(totalCount2);

        //计算总页数
        int totalPage = totalCount2 / pageSize;
        if (totalCount2 % pageSize != 0) {
            totalPage++;
        }
        pageBean.setTotalPage(totalPage);
        List<ClientVisit> list = clientVisitDao.findByPage(detachedCriteria, currentPage, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public void delete(ClientVisit model) {
        clientVisitDao.delete(model);
    }
}
