package com.iu.kmi.data.entity;

public class ConditionEntity {
    private int id;
    private String name;
    private String paymentMethod;
    private String termsOfDelivery;
    private double discount;

    public ConditionEntity(String name, String paymentMethod, String termsOfDelivery, double discount) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.termsOfDelivery = termsOfDelivery;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTermsOfDelivery() {
        return termsOfDelivery;
    }

    public void setTermsOfDelivery(String termsOfDelivery) {
        this.termsOfDelivery = termsOfDelivery;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}