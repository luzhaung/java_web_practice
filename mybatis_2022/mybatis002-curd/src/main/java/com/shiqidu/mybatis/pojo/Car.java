package com.shiqidu.mybatis.pojo;

public class Car {
    private Integer id;
    private String car_no;
    private String brand;
    private Double guide_price;
    private String produce_time;
    private String car_type;

    public Car() {
    }

    public Car(Integer id, String car_no, String brand, Double guide_price, String produce_time, String car_type) {
        this.id = id;
        this.car_no = car_no;
        this.brand = brand;
        this.guide_price = guide_price;
        this.produce_time = produce_time;
        this.car_type = car_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCar_no() {
        return car_no;
    }

    public void setCar_no(String car_no) {
        this.car_no = car_no;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(Double guide_price) {
        this.guide_price = guide_price;
    }

    public String getProduce_time() {
        return produce_time;
    }

    public void setProduce_time(String produce_time) {
        this.produce_time = produce_time;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", car_no='" + car_no + '\'' +
                ", brand='" + brand + '\'' +
                ", guide_price=" + guide_price +
                ", produce_time='" + produce_time + '\'' +
                ", car_type='" + car_type + '\'' +
                '}';
    }
}
