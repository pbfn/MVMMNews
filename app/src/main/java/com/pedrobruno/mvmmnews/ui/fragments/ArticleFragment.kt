package com.pedrobruno.mvmmnews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pedrobruno.mvmmnews.databinding.FragmentArticleBinding
import com.pedrobruno.mvmmnews.models.Article
import com.pedrobruno.mvmmnews.ui.NewsActivity
import com.pedrobruno.mvmmnews.ui.NewsViewModel

class ArticleFragment : Fragment() {
    private lateinit var binding: FragmentArticleBinding
    private lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()
    private lateinit var article: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        getBundle()
        setupWebView()
        saveArticle()
    }

    fun getBundle() {
        article = args.article
    }

    fun setupWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    fun saveArticle() {
        binding.fab.setOnClickListener {
            viewModel.saveArticle(article)
            view?.let { it1 ->
                Snackbar.make(
                    it1,
                    "Article saved successfully",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }
}