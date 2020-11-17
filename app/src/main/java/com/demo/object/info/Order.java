package com.demo.object.info;

import java.sql.Date;

public class Order {
    private int orderId;
    private float totalMoney;
    private String orderDay;
    private int tableId;
    private int userId;
    private int status;

    public Order(int orderId, float totalMoney, String orderDay, int tableId, int userId, int status) {
        this.orderId = orderId;
        this.totalMoney = totalMoney;
        this.orderDay = orderDay;
        this.tableId = tableId;
        this.userId = userId;
        this.status = status;
    }

    public Order(float totalMoney, String orderDay, int tableId, int userId, int status) {
        this.totalMoney = totalMoney;
        this.orderDay = orderDay;
        this.tableId = tableId;
        this.userId = userId;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
