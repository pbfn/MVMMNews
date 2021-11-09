package com.pedrobruno.mvmmnews.db

import androidx.room.TypeConverter
import com.pedrobruno.mvmmnews.models.Source

class Convertes {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}