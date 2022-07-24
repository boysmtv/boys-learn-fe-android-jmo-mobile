package com.boys.assets.jmo.activity.menu.fragment.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.boys.assets.jmo.R

class ContactFragment : Fragment() {

    private val TAG = this::class.java.simpleName

    companion object {
        fun getInstance() : ContactFragment = ContactFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_menu_fragment_contact, container, false)
    }

}