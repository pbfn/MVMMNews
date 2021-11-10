package com.pedrobruno.mvmmnews.repository

import com.pedrobruno.mvmmnews.api.RetrofitInstance
import com.pedrobruno.mvmmnews.db.ArticleDatabase

class NewsRepository(
    val db: ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode: String,pageNumber:Int) =
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}