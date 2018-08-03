package com.itheima.crm.web.action;

import com.itheima.crm.domain.ClientVisit;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ClientVisitService;
import com.opensymphony.xwork2.ActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ClientVisitAction extends BaseAction<ClientVisit> {
    @Autowired
    private ClientVisitService clientVisitService;


    public String add() {
        System.out.println("add..........");
        return "addVist";

    }

    public String save() {
        clientVisitService.save(model);
        return "saveSuccess";
    }

    private Date visit_end_time;

    public Date getVisit_end_time() {
        return visit_end_time;
    }

    public void setVisit_end_time(Date visit_end_time) {
        this.visit_end_time = visit_end_time;
    }

    public String findByPage() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ClientVisit.class);
        //添加查询条件
            if(model.getVisit_time()!=null){
                detachedCriteria.add(Restrictions.ge("visit_time",model.getVisit_time()));
        }

        //拜访的结束时间需要使用属性注入;
        if(visit_end_time!=null){
            detachedCriteria.add(Restrictions.le("visit_time",visit_end_time));
        }

        PageBean<ClientVisit> pageBean = clientVisitService.findByPage(detachedCriteria, currentPage, pageSize);
        System.out.println(pageBean);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "page";
    }

    public String delete() {
        clientVisitService.delete(model);
        return "deleteSuccess";
    }



}
