package com.rolfrider.tapper.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ScoreDB::class], version = 1, exportSchema = false)
abstract class ScoreDatabase: RoomDatabase() {

    abstract fun scores(): ScoreDao

    companion object{
        lateinit var INSTANCE: ScoreDatabase

        fun initIfNeeded(context: Context){
            if (ScoreDatabase.Companion::INSTANCE.isInitialized.not()){
                INSTANCE = Room.databaseBuilder(context, ScoreDatabase::class.java, "score_db")
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}