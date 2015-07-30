package com.ztx.qa.learn1;

import javax.persistence.*;

/**
 * Created by s016374 on 15/7/29.
 */
@Entity
@Table
public class Manager {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String mgrName;
    @OneToOne(mappedBy = "manager")
    private Department department;

    public Manager() {
    }

    public Manager(String mgrName) {
        this.mgrName = mgrName;
    }

    public Manager(Department department) {
        this.department = department;
    }

    public Manager(String mgrName, Department department) {
        this.mgrName = mgrName;
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMgrName() {
        return mgrName;
    }

    public void setMgrName(String mgrName) {
        this.mgrName = mgrName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", mgrName='" + mgrName + '\'' +
                ", department=" + department +
                '}';
    }
}
