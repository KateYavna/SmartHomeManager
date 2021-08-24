package com.example.smarthomemanager.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CurrentReadingsDao {
    @Query("SELECT * FROM CurrentReadings")
    List<CurrentReadings> getAll();

    @Query("SELECT * FROM CurrentReadings WHERE data = :data")
    CurrentReadings getByData(String data);

    @Insert
    void insert (CurrentReadings currentReadings);

    @Update
    void update (CurrentReadings currentReadings);

    @Delete
    void delete (CurrentReadings currentReadings);
}
