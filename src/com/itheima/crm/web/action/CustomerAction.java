package com.itheima.crm.web.action;


import com.google.gson.*;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.LinkMan;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.CustomerService;
import com.itheima.crm.service.impl.CustomerServiceImpl;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.config.json.JsonConfiguration;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerAction extends BaseAction<Customer> {


    @Autowired
    private CustomerService customerService;

//    public void setCustomerService(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    public String add() {
        System.out.println("add................");
        return SUCCESS;
    }

    //struts的action集成了文件上传的三个属性,用来注入表单上传时的文件,注意提供set方法注入(属性注入);
    private File upload;
    private String uploadFileName;
    private String uploadContentType;

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String save() throws IOException {
        System.out.println("save................");

        //实现文件上传
        //1.设置文件上传的路径
        if (upload != null) {
            //设置保存的根路径
            String path = "E:/upload";
            //获取上传文件的唯一文件名
            String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
            //根据唯一文件名计算得出唯一的存放文件夹路径
            String path2 = UploadUtils.getPath(uuidFileName);

            //拼接文件保存的路径--->根路径+唯一文件保存路径
            String realPath = path + path2;
            System.out.println("realPath.........." + realPath);

            //使用File类创建该路径的相关文件夹
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //创建保存的目标文件
            File file2 = new File(realPath + "/" + uuidFileName);
            //使用工具类将上传的文件保存到目标文件中
            FileUtils.copyFile(upload, file2);
            //将文件路径存到到Customer对象中,并保存到数据库
            model.setCust_image(realPath + "/" + uuidFileName);
        }

        customerService.save(model);
        return "saveSuccess";
    }

    //使用struts-json-plugin插件包实现自动转json
    public String findAll(){
        List<Customer> list = customerService.findAll();

        //将list存到根栈区
        ActionContext.getContext().getValueStack().push(list);
        return "jsonSuccess";
    }

//    public String findAll() throws IOException {
//        System.out.println("customerAction  findAll........");
//        List<Customer> list = customerService.findAll();
//
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setExcludes(new String[]{"linkMans", "baseDictSource", "baseDictIndustry", "baseDictLevel"});
//
//        JSONArray json = JSONArray.fromObject(list, jsonConfig);
//        System.out.println(json);
//
//        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
//        ServletActionContext.getResponse().getWriter().println(json);
//        return null;
//    }


//    //定义当前页面和每页的显示的数量---->用于属性驱动
//    private Integer currentPage = 1;
//    private Integer pageSize = 3;
//
//    public void setCurrentPage(Integer currentPage) {
//        if (currentPage == null) {
//            currentPage = 1;
//        }
//
//        this.currentPage = currentPage;
//    }
//
//    public void setPageSize(Integer pageSize) {
//        if (pageSize == null) {
//            pageSize = 3;
//        }
//        this.pageSize = pageSize;
//    }

    //使用离线查询
    public String findByPage() {
        System.out.println("findByPage.............");
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        //条件查询
        if (StringUtils.isNotBlank(model.getCust_name())) {
            detachedCriteria.add(Restrictions.like("cust_name", model.getCust_name(), MatchMode.ANYWHERE));
        }
        if (model.getBaseDictLevel() != null && model.getBaseDictLevel().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", model.getBaseDictLevel().getDict_id()));
        }
        if (model.getBaseDictSource() != null && model.getBaseDictSource().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", model.getBaseDictSource().getDict_id()));
        }
        if (model.getBaseDictIndustry() != null && model.getBaseDictIndustry().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", model.getBaseDictIndustry().getDict_id()));
        }
        currentPage=page;
        pageSize=rows;

        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currentPage, pageSize);
        System.out.println(pageBean);

        Map<String,Object> map=new HashMap<>();
        map.put("total",pageBean.getTotalCount());
        map.put("rows",pageBean.getList());
        ServletActionContext.getContext().getValueStack().push(map);

        return "pageSuccess";
    }

    //删除功能--->先查询保存的文件路径,删除文件后再删除数据库中的数据
    public String delete() {
        System.out.println("delete...........");
        Customer customer2 = customerService.findById(model.getCust_id());
        System.out.println("customer2--->" + customer2);
        if (customer2.getCust_image() != null) {
            File file = new File(customer2.getCust_image());

            if (file.exists()) {
                file.delete();
            }
        }

        customerService.delete(customer2);
        return "deleteSuccess";
    }

    //修改操作的数据回显
    public String update() {
        Customer customer2 = customerService.findById(this.model.getCust_id());
        ActionContext.getContext().getValueStack().push(customer2);
        return "echoSuccess";

    }


    //修改数据的操作
    public String updateData() throws IOException {
        System.out.println("updateData..........");
        System.out.println(model);
        if (upload != null) {
            File file3 = new File(model.getCust_image());
            if (file3.exists()) {
                file3.delete();
            }
            //设置保存的根路径
            String path = "E:/upload";
            //获取上传文件的唯一文件名
            String uuidFileName = UploadUtils.getUUIDFileName(uploadFileName);
            //根据唯一文件名计算得出唯一的存放文件夹路径
            String path2 = UploadUtils.getPath(uuidFileName);

            //拼接文件保存的路径--->根路径+唯一文件保存路径
            String realPath = path + path2;
            System.out.println("realPath.........." + realPath);

            //使用File类创建该路径的相关文件夹
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            //创建保存的目标文件
            File file2 = new File(realPath + "/" + uuidFileName);
            //使用工具类将上传的文件保存到目标文件中
            FileUtils.copyFile(upload, file2);
            //将文件路径存到到Customer对象中,并保存到数据库
            model.setCust_image(realPath + "/" + uuidFileName);

        }
        customerService.update(model);
        return "updateSuccess";
    }


}







