package com.itheima.crm.web.action;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    //刚加的代码
    private String name;
    //testSVN02修改的代码
    private String testSVN02;

    //testSVN03修改的代码
    private String testO3SVN;

    User user = new User();

    @Override
    public User getModel() {
        return user;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String regist() {

        return "regist";

    }

    public String registSave() {
        System.out.println(user);
        userService.save(user);
        return SUCCESS;
    }

    public String login() {
        User user2 = userService.find(user);
        System.out.println("user2---------->"+user2);
        if (user2!=null){
            ServletActionContext.getRequest().getSession().setAttribute("existUser",user2);
            return "login";
        }else {
            addActionError("用户名或者密码错误");
            return "failed";
        }
    }


}
