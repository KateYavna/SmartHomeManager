package com.example.smarthomemanager.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CurrentReadings {

    @PrimaryKey
    @NonNull
    public String data;
    public String value;

    public CurrentReadings(@NonNull String data, String value) {
        this.data = data;
        this.value = value;
    }

    @NonNull
    public String getData() {
        return data;
    }

    public String getValue() {
        return value;
    }

    public void setData(@NonNull String data) {
        this.data = data;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
