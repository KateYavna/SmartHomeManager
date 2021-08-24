package com.example.smarthomemanager.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CurrentReadings.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
        public abstract CurrentReadingsDao currentReadingsDao();
}
