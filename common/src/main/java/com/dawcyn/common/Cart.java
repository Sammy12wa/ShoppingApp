package com.dawcyn.common;

/*
 * Created by DAWCYN on 8/7/2018.
 */

public class Cart {
    String id;
    String name;
    String price;
    String quantity;
    String cost;
    String code;
    String testImage;

    public Cart(){}

    public Cart(String name,String price,String quantity,String cost,
                String image){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.cost=cost;
        this.testImage=image;
    }

    public Cart(String code,String name,String price,String quantity,String cost,
                String image){
        this.code=code;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.cost=cost;
        this.testImage=image;
    }

    public String getTestImage() {
        return testImage;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
