package com.boys.assets.jmo.activity.menu.fragment.home.adaper

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boys.assets.jmo.databinding.ActivityMenuFragmentHomeItemBinding
import com.boys.assets.jmo.helper.OnItemClickListener

class HomeFragmentAdapter(listener : OnItemClickListener) : RecyclerView.Adapter<HomeFragmentAdapter.AdapterHolder>() {

    private val thisListener : OnItemClickListener = listener
    private val strTitle = arrayOf(
        "Selesaikan pendaftaran untuk mendapatkan kupon percobaan Rp 500.000",
        "Selesaikan verifikasi untuk mendapatkan kupon tunai Rp 100.000",
        "Dapatkan kupon bunga tambahan 1% setelah pendanaan pertama anda")

    //get the size of color array
    override fun getItemCount(): Int = strTitle.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMenuFragmentHomeItemBinding.inflate(inflater, parent, false)
        return AdapterHolder(binding)
    }

    //binding the screen with view
    override fun onBindViewHolder(holder: AdapterHolder, position: Int) = holder.itemView.run {
        holder.binding.tvCarouselTitle.text = strTitle[position]

        holder.bind(position, thisListener)
    }

    inner class AdapterHolder(val binding: ActivityMenuFragmentHomeItemBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(position: Int, listener: OnItemClickListener) {
            binding.btnUserCarouselItem1.setOnClickListener { view ->
                listener.onItemClick(
                    view,
                    0,
                    position
                )
            }
        }
    }

}
