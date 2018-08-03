package com.itheima.crm.domain;

import java.util.Date;

public class ClientVisit {
    private Long visit_id;
    private Date visit_time;
    private String visit_location;
    private String visit_detail;
    private Date visit_nexttime;

    private Customer customer;
    private User user;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getVisit_id() {
        return visit_id;
    }

    public void setVisit_id(Long visit_id) {
        this.visit_id = visit_id;
    }

    public Date getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(Date visit_time) {
        this.visit_time = visit_time;
    }

    public String getVisit_location() {
        return visit_location;
    }

    public void setVisit_location(String visit_location) {
        this.visit_location = visit_location;
    }

    public String getVisit_detail() {
        return visit_detail;
    }

    public void setVisit_detail(String visit_detail) {
        this.visit_detail = visit_detail;
    }

    public Date getVisit_nexttime() {
        return visit_nexttime;
    }

    public void setVisit_nexttime(Date visit_nexttime) {
        this.visit_nexttime = visit_nexttime;
    }

    @Override
    public String toString() {
        return "ClientVisit{" +
                "visit_id=" + visit_id +
                ", visit_time=" + visit_time +
                ", visit_location='" + visit_location + '\'' +
                ", visit_detail='" + visit_detail + '\'' +
                ", visit_nexttime=" + visit_nexttime +
                '}';
    }
}
