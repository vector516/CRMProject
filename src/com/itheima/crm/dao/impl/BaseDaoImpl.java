package com.itheima.crm.dao.impl;

import com.itheima.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    //通过空参构造中的泛型的反射获得子类的实例化对象---->从而获得子类中携带的JavaBean对象的字节码文件(存储数据的容器)
    //用来传入hibernate查询
    public BaseDaoImpl() {
        //当spring初始化子类Dao对象时首先调用了父类的空参构造方法,因此this即代表子类对象
        Class c = this.getClass();
        //获取带有泛型的父类
        Type type = c.getGenericSuperclass();
        //将type(泛型)转化为参数类型
        ParameterizedType parameterizedType= (ParameterizedType) type;
        //通过该参数类型获得实际的类型---->得到一个数组
        Type[] types = parameterizedType.getActualTypeArguments();
        //取0号元素即为实际类型--->字节码文件对象
         this.clazz= (Class)types[0];
        System.out.println("clazz------>"+clazz);
        System.out.println("types[0]------>"+types[0]);
    }


//    //通过带参构造方法将需要查询并封装的对象传进来,替换T
//    public BaseDaoImpl(Class clazz) {
//        this.clazz = clazz;
//    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public T findById(Long id) {
        Object o = getHibernateTemplate().get(this.clazz, id);
        return (T) o;
    }

    @Override
    public Long findCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<T> findByPage(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
      detachedCriteria.setProjection(null);
        List<T> list = (List<T>) getHibernateTemplate().findByCriteria(detachedCriteria,(currentPage-1)*pageSize,pageSize);
        return list;
    }

    @Override
    public List<T> findAll() {
        List<T> list = (List<T>) getHibernateTemplate().find("from " + this.clazz.getSimpleName());
        return list;
    }
}
