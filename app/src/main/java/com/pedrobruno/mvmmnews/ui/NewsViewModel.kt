package com.pedrobruno.mvmmnews.ui

import androidx.lifecycle.ViewModel
import com.pedrobruno.mvmmnews.repository.NewsRepository

class NewsViewModel(
    val newsRepository: NewsRepository
): ViewModel() {
}