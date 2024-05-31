package com.iu.kmi.data.entity;

/**
 * Represents a set of conditions including payment method, terms of delivery, and discount.
 */
public class ConditionEntity {
    private int id;
    private String name;
    private String paymentMethod;
    private String termsOfDelivery;
    private double discount;

    /**
     * Constructs a new ConditionEntity with the specified name, payment method, terms of delivery, and discount.
     *
     * @param name the name of the condition
     * @param paymentMethod the payment method associated with the condition
     * @param termsOfDelivery the terms of delivery associated with the condition
     * @param discount the discount rate associated with the condition
     */
    public ConditionEntity(String name, String paymentMethod, String termsOfDelivery, double discount) {
        this.name = name;
        this.paymentMethod = paymentMethod;
        this.termsOfDelivery = termsOfDelivery;
        this.discount = discount;
    }

    /**
     * Gets the ID of the condition.
     *
     * @return the ID of the condition
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the condition.
     *
     * @param id the new ID of the condition
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the condition.
     *
     * @return the name of the condition
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the condition.
     *
     * @param name the new name of the condition
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the payment method associated with the condition.
     *
     * @return the payment method associated with the condition
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * Sets the payment method associated with the condition.
     *
     * @param paymentMethod the new payment method associated with the condition
     */
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * Gets the terms of delivery associated with the condition.
     *
     * @return the terms of delivery associated with the condition
     */
    public String getTermsOfDelivery() {
        return termsOfDelivery;
    }

    /**
     * Sets the terms of delivery associated with the condition.
     *
     * @param termsOfDelivery the new terms of delivery associated with the condition
     */
    public void setTermsOfDelivery(String termsOfDelivery) {
        this.termsOfDelivery = termsOfDelivery;
    }

    /**
     * Gets the discount rate associated with the condition.
     *
     * @return the discount rate associated with the condition
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Sets the discount rate associated with the condition.
     *
     * @param discount the new discount rate associated with the condition
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "ConditionEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", termsOfDelivery='" + termsOfDelivery + '\'' +
                ", discount=" + discount +
                '}';
    }
}
