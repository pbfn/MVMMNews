package com.pedrobruno.mvmmnews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pedrobruno.mvmmnews.databinding.FragmentSearchNewsBinding
import com.pedrobruno.mvmmnews.ui.NewsActivity
import com.pedrobruno.mvmmnews.ui.NewsViewModel


class SearchNewsFragment:Fragment() {

    private lateinit var binding: FragmentSearchNewsBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchNewsBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
    }
}