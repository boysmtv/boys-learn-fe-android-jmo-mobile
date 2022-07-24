package com.boys.assets.jmo.activity.menu.fragment.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.jmo.R
import com.boys.assets.jmo.activity.menu.fragment.posts.adapter.PostsAdapter
import com.boys.assets.jmo.activity.menu.fragment.posts.presentation.PostsViewModel
import com.boys.assets.jmo.utils.isNetworkAvailable
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private val TAG = this::class.java.simpleName
    private val financeVM by viewModel<PostsViewModel>()

    companion object {
        fun getInstance() : PostsFragment = PostsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_menu_fragment_berita, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNews(view)
    }

    private fun setNews(view: View) {
        val newsAdapter = PostsAdapter()
        val rvNews : RecyclerView = view.findViewById(R.id.rv_menu_fragment_recyclerview)
        rvNews.adapter = newsAdapter

        if (requireContext().isNetworkAvailable()) {
            financeVM.getFlash()
        } else {
            Toast.makeText(
                requireContext(),
                getString(R.string.no_internet_connection),
                Toast.LENGTH_SHORT
            ).show()
        }

        with(financeVM) {
            onSuccess.observe(viewLifecycleOwner) {
                newsAdapter.mFlashList = it
            }
            onError.observe(viewLifecycleOwner) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}