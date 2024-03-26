package com.example.Galley.DataBase2.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table1")
public class Database2Entity {

    @Id
    @Column(name = "columone")
    private String columone;

    @Column(name = "columtwo")
    private String columtwo;

    public Database2Entity() {
    }

    public Database2Entity(String columone, String columtwo) {
        this.columone = columone;
        this.columtwo = columtwo;
    }

    public String getColumone() {
        return columone;
    }

    public void setColumone(String columone) {
        this.columone = columone;
    }

    public String getColumtwo() {
        return columtwo;
    }

    public void setColumtwo(String columtwo) {
        this.columtwo = columtwo;
    }
}
