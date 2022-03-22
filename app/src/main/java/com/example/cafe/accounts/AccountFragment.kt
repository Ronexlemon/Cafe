package com.example.cafe.accounts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cafe.MainActivity
import com.example.cafe.R
import com.example.cafe.R.color.*
import com.example.cafe.databinding.FragmentAccountBinding


class AccountFragment : Fragment() {
    private lateinit var binding: FragmentAccountBinding
   


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_account,container,false)
        binding.delete.setOnClickListener {
            account()
        }
        binding.back.setIconResource(R.drawable.ic_baseline_home_24)
        binding.back.setOnClickListener {
    val intent = Intent(activity, MainActivity::class.java)
           startActivity(intent)

        }
        return binding.root
    }


    private fun account() {
view!!.findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToDeleteFragment())
    }


}