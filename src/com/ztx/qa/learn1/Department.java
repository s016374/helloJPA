package com.ztx.qa.learn1;

import javax.persistence.*;

/**
 * Created by s016374 on 15/7/29.
 */
@Entity
@Table
public class Department {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String deptName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Manager manager;

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public Department(Manager manager) {
        this.manager = manager;
    }

    public Department(Manager manager, String deptName) {
        this.manager = manager;
        this.deptName = deptName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", manager=" + manager +
                '}';
    }
}
