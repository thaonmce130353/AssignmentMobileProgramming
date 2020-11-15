package com.demo.object.info;

public class OrderDetail {
    private int orderDetailId;
    private float price;
    private float priceAfterSaleOff;
    private int percentSaleOff;
    private int quantity;
    private float total;
    private int orderId;
    private int productId;

    public OrderDetail(int orderDetailId, float price, float priceAfterSaleOff, int percentSaleOff, int quantity, float total, int orderId, int productId) {
        this.orderDetailId = orderDetailId;
        this.price = price;
        this.priceAfterSaleOff = priceAfterSaleOff;
        this.percentSaleOff = percentSaleOff;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderDetail(float price, float priceAfterSaleOff, int percentSaleOff, int quantity, float total, int orderId, int productId) {
        this.price = price;
        this.priceAfterSaleOff = priceAfterSaleOff;
        this.percentSaleOff = percentSaleOff;
        this.quantity = quantity;
        this.total = total;
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getorderDetailId() {
        return orderDetailId;
    }

    public void setorderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceAfterSaleOff() {
        return priceAfterSaleOff;
    }

    public void setPriceAfterSaleOff(float priceAfterSaleOff) {
        this.priceAfterSaleOff = priceAfterSaleOff;
    }

    public int getPercentSaleOff() {
        return percentSaleOff;
    }

    public void setPercentSaleOff(int percentSaleOff) {
        this.percentSaleOff = percentSaleOff;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
