package com.example.newsit.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsit.adapters.NewsAdapter
import com.example.newsit.databinding.FragmentBreakingNewsBinding
import com.example.newsit.ui.NewsActivity
import com.example.newsit.ui.NewsViewModel
import com.example.newsit.util.Resource

class BreakingNewsFragment:Fragment() {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    //binding variable
    private lateinit var binding: FragmentBreakingNewsBinding

    val TAG="BreakingNewsFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as NewsActivity).viewModel
        setupRecyclerView()

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer {response ->
        when(response){
            is Resource.Success ->{
                hideProgressBar()
                response.data?.let { newsResponse ->
                    newsAdapter.differ.submitList(newsResponse.articles)
                }
            }
            is Resource.Error ->{
                hideProgressBar()
                response.message?.let{message ->
                    Log.e(TAG, "An error occured: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()
            }
        }
        })
    }

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility=View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.paginationProgressBar.visibility=View.VISIBLE
    }
    private fun setupRecyclerView(){
        newsAdapter= NewsAdapter()
        binding.rvBreakingNews.apply {
            adapter=newsAdapter
            layoutManager=LinearLayoutManager(activity)
        }
    }
}