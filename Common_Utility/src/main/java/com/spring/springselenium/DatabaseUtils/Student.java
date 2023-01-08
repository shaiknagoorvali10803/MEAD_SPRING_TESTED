package com.spring.springselenium.DatabaseUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Student")
public class Student {
    @Id
    private  int sno;
    private String name;
    private String address;

    public Student() {
    }

    public Student(int sno, String name, String address) {
        this.sno = sno;
        this.name = name;
        this.address = address;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
