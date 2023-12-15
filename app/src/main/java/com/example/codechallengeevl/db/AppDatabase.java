package com.example.codechallengeevl.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DaoBeer daoBeer();
}
