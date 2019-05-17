package com.framgia.orderfood.data.model;

public class Table {
    String Name, Status;

    public Table(String name, String status) {
        Name = name;
        Status = status;
    }
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
