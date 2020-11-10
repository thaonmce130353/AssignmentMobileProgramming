package com.demo.object.info;

import android.graphics.Bitmap;

public class Image {
    private int id;
    private byte[] url;
    private boolean status;
    private int productId;

    public Image(int id, byte[] url, boolean status, int productId) {
        this.id = id;
        this.url = url;
        this.status = status;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getUrl() {
        return url;
    }

    public void setUrl(byte[] url) {
        this.url = url;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
