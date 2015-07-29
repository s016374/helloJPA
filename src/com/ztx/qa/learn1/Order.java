package com.ztx.qa.learn1;

import javax.persistence.*;

/**
 * Created by s016374 on 15/7/29.
 */
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private int ID;
    @Column(name = "ORDER_NAME")
    private String orderName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public Order() {
    }

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
        this.customer = customer;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "ID=" + ID +
                ", orderName='" + orderName + '\'' +
                ", customer=" + customer +
                '}';
    }
}
