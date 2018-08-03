package com.itheima.crm.service.impl;

import com.itheima.crm.dao.LinkManDao;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(readOnly = false)
public class LinkManServiceImpl implements LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public PageBean<LinkMan> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
        PageBean<LinkMan> pageBean = new PageBean<>();
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        //查询总数据条数
        Long totalCount = linkManDao.findCount(detachedCriteria);

        int totalCount2 = totalCount.intValue();
        pageBean.setTotalCount(totalCount2);

        //计算总页数
        int totalPage = totalCount2 / pageSize;
        if (totalCount2 % pageSize != 0) {
            totalPage++;
        }
        pageBean.setTotalPage(totalPage);
        List<LinkMan> list = linkManDao.findByPage(detachedCriteria, currentPage, pageSize);
        pageBean.setList(list);
        return pageBean;
    }

    @Override
    public LinkMan findById(Long lkm_id) {
        LinkMan linkMan= linkManDao.findById(lkm_id);
        return linkMan;
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }

    @Override
    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }
}
