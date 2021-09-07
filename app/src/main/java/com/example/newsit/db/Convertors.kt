package com.example.newsit.db

import androidx.room.TypeConverter
import com.example.newsit.models.Source

class Convertors {
    @TypeConverter
    fun fromSource(source:Source):String{
        return source.name
    }
    @TypeConverter
    fun getSource(name:String):Source{
        return Source(name,name)
    }
}