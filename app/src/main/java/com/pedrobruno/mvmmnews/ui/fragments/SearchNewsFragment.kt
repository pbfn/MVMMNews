package com.pedrobruno.mvmmnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedrobruno.mvmmnews.R
import com.pedrobruno.mvmmnews.adapters.NewsAdapter
import com.pedrobruno.mvmmnews.databinding.FragmentSearchNewsBinding
import com.pedrobruno.mvmmnews.ui.NewsActivity
import com.pedrobruno.mvmmnews.ui.NewsViewModel
import com.pedrobruno.mvmmnews.util.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import com.pedrobruno.mvmmnews.util.Resource
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchNewsFragment : Fragment() {

    private lateinit var binding: FragmentSearchNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    val TAG = "SearchNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchNewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        setupRecyclerView()
        observeData()
        setupSearchNews()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        setupClickAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun setupClickAdapter() {
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
    }

    private fun observeData() {
        viewModel.searchNews.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgessBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgessBar()
                    response.message?.let { message ->
                        Log.e(TAG, "Ocorreu um erro $message")
                    }
                }
                is Resource.Loading -> {
                    showProgessBar()
                }
            }
        })
    }

    private fun setupSearchNews() {
        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }
    }

    private fun hideProgessBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgessBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }
}