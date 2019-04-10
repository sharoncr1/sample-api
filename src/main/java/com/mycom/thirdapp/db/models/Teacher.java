package com.mycom.thirdapp.db.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="teacher")
public class Teacher{

    @Id
    @Column(name="id")
    @Size(max = 3)
    private String id;

    @Size(max = 50)
    @Column(name="name")
    @NotNull
    private String name;

    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Teacher(){

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

}
