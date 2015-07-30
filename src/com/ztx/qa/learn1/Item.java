package com.ztx.qa.learn1;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by s016374 on 15/7/30.
 */
@Entity
@Table
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String itemName;
    @ManyToMany
//    @JoinTable(joinColumns = {@JoinColumn},inverseJoinColumns = {@JoinColumn})
    @JoinTable
    private Set<Category> categories = new HashSet<>();

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(Set<Category> categories) {
        this.categories = categories;
    }

    public Item(String itemName, Set<Category> categories) {
        this.itemName = itemName;
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", categories=" + categories +
                '}';
    }
}
