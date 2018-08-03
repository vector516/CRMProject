package com.itheima.crm.service.impl;

import com.itheima.crm.dao.BaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.BaseDictService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public class BaseDictServiceImpl implements BaseDictService {

    private BaseDictDao baseDictDao;

    public void setBaseDictDao(BaseDictDao baseDictDao) {
        this.baseDictDao = baseDictDao;
    }

    @Override
    @RequestMapping("/jquery/ajax/responseJsonArray")
    @ResponseBody
    public List<BaseDict> findByTypeCode(String dict_type_code) {
        List<BaseDict> list=baseDictDao.findByTypeCode(dict_type_code);
        return  list;
    }
}
