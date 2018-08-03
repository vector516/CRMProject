package com.itheima.crm.interceptor;

import com.itheima.crm.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        //判断session中是否存在user对象
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if (user == null) {
            ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
            actionSupport.addActionError("用户未登录无法操作");
            return "loginError";
        }else {

            return actionInvocation.invoke();
        }
    }
}
