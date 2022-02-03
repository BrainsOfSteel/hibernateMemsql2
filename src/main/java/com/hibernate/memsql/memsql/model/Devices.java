package com.hibernate.memsql.memsql.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
public class Devices {
    @Id
    private Integer id;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "model_number")
    private String modelNumber;

    public Devices() {
    }

    public Devices(Integer id, String deviceName, String modelNumber) {
        this.id = id;
        this.deviceName = deviceName;
        this.modelNumber = modelNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    @Override
    public String toString() {
        return "Devices{" +
                "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", modelNumber='" + modelNumber + '\'' +
                '}';
    }
}
