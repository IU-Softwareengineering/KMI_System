package com.iu.kmi.entities;

public class CustomerRequest {
    private String id;
    private String customer;
    private String request_date;
    private String request_description;
    private String priority;
    private String status;

    // Konstruktor
    public CustomerRequest(String id, String customer, String request_date, String request_description, String priority, String status) {
        this.id = id;
        this.customer = customer;
        this.request_date = request_date;
        this.request_description = request_description;
        this.priority = priority;
        this.status = status;
    }

    // Getter- und Setter-Methoden
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcustomer() {
        return customer;
    }

    public void setcustomer(String customer) {
        this.customer = customer;
    }

    public String getrequest_date() {
        return request_date;
    }

    public void setrequest_date(String request_date) {
        this.request_date = request_date;
    }

    public String getrequest_description() {
        return request_description;
    }

    public void setrequest_description(String request_description) {
        this.request_description = request_description;
    }

    public String getpriority() {
        return priority;
    }

    public void setpriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "customerrequest{" +
                "id=" + id +
                ", customer=" + customer +
                ", request_date='" + request_date + '\'' +
                ", request_description='" + request_description + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}