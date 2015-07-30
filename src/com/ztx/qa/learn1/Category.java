package com.ztx.qa.learn1;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by s016374 on 15/7/30.
 */
@Entity
@Table
public class Category {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String categoryName;
    @ManyToMany(mappedBy = "categories")
    Set<Item> items = new HashSet<>();


    public Category() {
    }

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category(Set<Item> items) {
        this.items = items;
    }

    public Category(String categoryName, Set<Item> items) {
        this.categoryName = categoryName;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", items=" + items +
                '}';
    }
}
