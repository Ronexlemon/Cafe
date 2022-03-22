package com.example.cafe

import android.app.Activity
import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController

import com.example.cafe.databinding.FragmentLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginFragment : Fragment() {
    private lateinit var binding:FragmentLoginBinding
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        binding.signup.setOnClickListener {view:View ->
            Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToSignFragment())

        }
        binding.login.setOnClickListener { view:View ->
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val confirmPass = binding.passwords.text.toString()
            if (password.isEmpty() || confirmPass.isEmpty() || username.isEmpty()){
                Toast.makeText(activity,"Please insert password or username ",Toast.LENGTH_SHORT).show()
                }
            else {
                if (password == password) {

                    login(username, password)
                } else {
                    Toast.makeText(activity, "Miss matched password", Toast.LENGTH_SHORT).show()

                }
            }



           // Navigation.findNavController(view).navigate(LoginFragmentDirections.actionLoginFragmentToUploadFragment())
        }
        return binding.root

    }

    private fun login(username:String,password:String) {
        val progress = ProgressDialog(activity)
        progress.setTitle("Authenticating...")
            progress.show()
        database = FirebaseDatabase.getInstance().getReference("User")
        database.child(username).get().addOnSuccessListener {
            if(it.exists()){
                Toast.makeText(activity,"database user exits",Toast.LENGTH_SHORT).show()
                 val usernames = it.child("username").value
                val passwords = it.child("password").value
                if (username == usernames && password == passwords ){
                    progress.dismiss()
                    view!!.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToUploadFragment())

                }
                else{
                    progress.dismiss()
                    Toast.makeText(activity,"please create an account",Toast.LENGTH_SHORT).show()
                }

            }
        }


    }


}