package com.example.cafe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.cafe.data.MyData
import com.example.cafe.databinding.FragmentSignBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignFragment : Fragment() {
    private lateinit var binding : FragmentSignBinding
    private lateinit var database:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign, container, false)
        binding.login.setIconResource(R.drawable.ic_baseline_arrow_back_24)
        binding.signup.setOnClickListener {
            val username = binding.username.text.toString()
            val password  = binding.passwd.text.toString()
            val mail = binding.email.text.toString()
            database=FirebaseDatabase.getInstance().getReference("User")
            val user = MyData(username,password,mail)
            database.child(username).setValue(user)
                .addOnSuccessListener {
               binding.username.text!!.clear()
                binding.passwd.text!!.clear()
                binding.email.text!!.clear()
                binding.confirm.text!!.clear()
                Toast.makeText(activity,"success",Toast.LENGTH_SHORT).show()
              view!!.findNavController().navigate(SignFragmentDirections.actionSignFragmentToUploadFragment())

            }.addOnFailureListener {
                Toast.makeText(activity,"failed",Toast.LENGTH_SHORT).show()
            }



        }
        binding.login.setOnClickListener{view:View ->
            view.findNavController()
                .navigate(SignFragmentDirections.actionSignFragmentToLoginFragment())

        }
        return binding.root


    }


}