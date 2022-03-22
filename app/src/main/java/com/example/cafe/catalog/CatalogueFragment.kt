package com.example.cafe.catalog

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import com.example.cafe.R
import com.example.cafe.databinding.FragmentCatalogueBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CatalogueFragment : Fragment() {
    private lateinit var binding : FragmentCatalogueBinding
    private lateinit var database:DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_catalogue,container,false)
        binding.getSearch.setOnClickListener {
            val username = binding.search.text.toString()
            getItem(username)

        }
        return binding.root
    }

    private fun getItem(userName:String) {
        val progress = ProgressDialog(activity)
        progress.setTitle("searching ......")
        progress.show()
         database = FirebaseDatabase.getInstance().getReference("User")
        database.child(userName).get().addOnSuccessListener {
            if(it.exists()){
                val mail = it.child("email").value
                val username = it.child("username").value
                val password = it.child("password").value
                progress.dismiss()
                binding.email.text = mail.toString()
                binding.password.text = password.toString()
                binding.username.text = username.toString()

                binding.search.text.clear()
                Toast.makeText(activity,"success",Toast.LENGTH_SHORT).show()

            }

        }
            .addOnFailureListener {
                Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT).show()
            }


    }


}