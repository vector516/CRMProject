package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManAction extends BaseAction<LinkMan> {
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public String add() {
        //同步加载Customer的信息到页面--->到数据库查一遍
        List<Customer> list = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("list",list);
        return SUCCESS;

    }

    public String save(){
        System.out.println("save................");
        linkManService.save(model);
        return "saveSuccess";
    }


    public String findByPage(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        //添加查询条件
        if(StringUtils.isNotBlank(model.getLkm_name())){
            detachedCriteria.add(Restrictions.like("lkm_name",model.getLkm_name(),MatchMode.ANYWHERE));
        }
        if(StringUtils.isNotBlank(model.getLkm_gender())){
            detachedCriteria.add(Restrictions.eq("lkm_gender",model.getLkm_gender()));
        }

        PageBean<LinkMan> pageBean = linkManService.findByPage(detachedCriteria, currentPage, pageSize);
        System.out.println(pageBean);
        ServletActionContext.getContext().getValueStack().push(pageBean);
        return "page";

    }


    //修改的操作
    public String edit(){
        System.out.println("edit............");
        //查询数据并回显
        //先查询客户对象回显到下拉列表
        List<Customer> list = customerService.findAll();
        ServletActionContext.getRequest().setAttribute("list",list);

        LinkMan linkMan2 =linkManService.findById(model.getLkm_id());
        ActionContext.getContext().getValueStack().push(linkMan2);
        return "edit";
    }

    public String update(){
        System.out.println("update....");
        linkManService.update(model);
        return "updateSuccess";
    }





    //删除的操作
    public String delete(){
        linkManService.delete(model);
        return "deleteSuccess";

    }



}
