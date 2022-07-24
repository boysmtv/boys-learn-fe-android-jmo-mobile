package com.boys.assets.jmo.activity.menu.fragment.posts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.jmo.activity.menu.fragment.posts.model.PostsModel
import com.boys.assets.jmo.databinding.ActivityMenuFragmentBeritaItemBinding
import kotlin.properties.Delegates

// for static adapeter
class PostsAdapter : RecyclerView.Adapter<PostsAdapter.Holder>() {

    var mFlashList: List<PostsModel>? by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    inner class Holder(val binding: ActivityMenuFragmentBeritaItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ActivityMenuFragmentBeritaItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val news = mFlashList?.get(position)

        if (news != null) {
            holder.binding.tvNewsTitle.text = news.title
        }
        if (news != null) {
            holder.binding.tvNewsDuration.text = news.body
        }

        if (news != null) {
            //Glide.with(holder.itemView.context).load(news.body).into(holder.binding.ivFinanceNewsIcon)
        }
    }

    override fun getItemCount(): Int = if (mFlashList.isNullOrEmpty()) 0 else mFlashList!!.size

}



