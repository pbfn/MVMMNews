package com.pedrobruno.mvmmnews.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.pedrobruno.mvmmnews.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }


//    @TypeConverter
//    fun listToJson(value: Source?): String {
//
//        return Gson().toJson(value)
//    }
//
//    @TypeConverter
//    fun jsonToList(value: String): Source {
//        val objects = Gson().fromJson(value, Source::class.java) as Source
//        val list = objects.toList()
//        return objects
//    }


}