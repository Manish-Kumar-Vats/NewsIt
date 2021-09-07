package com.example.newsit.repository

import com.example.newsit.api.RetrofitInstance
import com.example.newsit.db.ArticleDatabase

class NewsRepository(
    val db:ArticleDatabase
) {
    suspend fun getBreakingNews(countryCode:String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)
}