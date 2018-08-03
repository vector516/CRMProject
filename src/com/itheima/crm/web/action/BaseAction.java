package com.itheima.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 抽取BaseAction将模型驱动和属性驱动的部分集成
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
    protected T model;//注意这里的模型驱动没有new,因此需要通过构造方法获得model对象才行


    //构造方法获得model对象
    public BaseAction() {
        //获得子类对象
        Class c = this.getClass();
        //通过子类获得父类
        Type type = c.getGenericSuperclass();
        //将泛型参数化
        ParameterizedType parameterizedType= (ParameterizedType) type;
        //获得真实的泛型--->参数化实例
        Type[] types = parameterizedType.getActualTypeArguments();
        //获得Customer字节码文件对象
        Class c2= (Class) types[0];
        //需要根据字节码文件对象获得javaBean的实例化对象-->并赋值给model
        try {
            model= (T) c2.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public T getModel() {
        return model;
    }


    //分页部分的属性驱动集成
    protected Integer currentPage=1;
    protected Integer pageSize=3;

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    protected Integer page=1;
    protected Integer rows=3;

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
