package com.itheima.crm.web.action;

import com.google.gson.Gson;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RequestMapping()
public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    BaseDict baseDict = new BaseDict();

    @Override
    public BaseDict getModel() {
        return baseDict;
    }

    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }

    public String findByTypeCode() throws IOException {

        System.out.println("findByTypeCode......" + baseDict.getDict_type_code());
        List<BaseDict> list = baseDictService.findByTypeCode(baseDict.getDict_type_code());

        // 将list转换为JSON字符串
        Gson gson = new Gson();

        String json = gson.toJson(list);

        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
        ServletActionContext.getResponse().getWriter().write(json);
        return NONE;
    }






}
