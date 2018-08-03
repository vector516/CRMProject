package com.itheima.crm.web.action;

import com.itheima.crm.domain.Customer;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DownloadAction extends ActionSupport implements ModelDriven<Customer> {
    Customer customer=new Customer();
    @Override
    public Customer getModel() {
        return customer;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public InputStream getInputStream() throws FileNotFoundException {
        System.out.println("download   inputStream...........");
        System.out.println("image---->"+customer.getCust_image());
        File file = new File(customer.getCust_image());
        InputStream inputStream = new FileInputStream(file);
        String fileName = customer.getCust_image();
        ActionContext.getContext().getValueStack().set("fileName",fileName);
        return inputStream;
    }


    public String download() throws Exception {
        System.out.println("download..........");

        // 使用客户ID，查询客户信息

        String cust_image = customer.getCust_image();
        System.out.println(cust_image);

        String filename = cust_image.substring(cust_image.lastIndexOf("/")+1);

        // 通过文件名获取类型
        String contentType = ServletActionContext.getServletContext().getMimeType(filename);
        ServletActionContext.getRequest().setAttribute("contentType", contentType);
        ServletActionContext.getRequest().setAttribute("filename", filename);

        // 将文件的输入流，存放到值栈（模型驱动）
        File file = new File(cust_image);
        InputStream inputStream = new FileInputStream(file);
        customer.setInputStream(inputStream);
        return "download";

    }



}
