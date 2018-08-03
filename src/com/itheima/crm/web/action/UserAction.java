package com.itheima.crm.web.action;

import com.google.gson.Gson;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    User user = new User();
    private String name;

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
        System.out.println("user2---------->" + user2);
        if (user2 != null) {
            ServletActionContext.getRequest().getSession().setAttribute("existUser", user2);
            return "login";
        } else {
            addActionError("用户名或者密码错误");
            return "failed";
        }
    }

    public String findAll(){
        List<User> list = userService.findAll();
        ActionContext.getContext().getValueStack().push(list);

        return "jsonSuccess";

    }


//    public String findAll() throws IOException {
//        System.out.println("user findAll.......");
//        List<User> list = userService.findAll();
//
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//
//        ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
//        ServletActionContext.getResponse().getWriter().write(json);
//        return NONE;
//
//
//    }


}
