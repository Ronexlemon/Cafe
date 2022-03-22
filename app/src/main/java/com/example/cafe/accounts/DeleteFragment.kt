package com.example.cafe.accounts

import android.app.ProgressDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cafe.R
import com.example.cafe.databinding.FragmentDeleteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.*


class DeleteFragment : Fragment() {
    private lateinit var  binding:FragmentDeleteBinding
    private lateinit var database : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_delete,container,false)
        binding.delete1.setOnClickListener {
            Toast.makeText(activity,"clicked",Toast.LENGTH_SHORT).show()
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            val pass = binding.confirm.text.toString()
            deleteData(username,password)
            //getItems()

        }
        return binding.root
    }



    private fun deleteData(username:String,password:String) {
        MaterialAlertDialogBuilder(context!!,R.style.ThemeOverlay_MaterialComponents_Light)
            .setMessage(resources.getString(R.string.message))
            .setPositiveButton(resources.getString(R.string.positive)){dialog,which->
                val progress= ProgressDialog(activity)
                progress.setTitle("Deleting...")
                progress.show()
                database = FirebaseDatabase.getInstance().getReference("User")

                val query: Query = database.child(username)
                query.addListenerForSingleValueEvent(object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()){
                            val user =snapshot.child("username").value
                            val pass = snapshot.child("password").value

                            //Toast.makeText(activity,"Database exists",Toast.LENGTH_SHORT).show()
                            for(dataSnapshot in snapshot.children){
                                if(username == user && password == pass){
                                    dataSnapshot.ref.removeValue()
                                }
                                else{
                                    Toast.makeText(activity,"Wrong password or email",Toast.LENGTH_SHORT).show()
                                }
                                //dataSnapshot.ref.removeValue()

                            }
                            progress.dismiss()
                            Toast.makeText(activity,"Removed account Successful",Toast.LENGTH_SHORT).show()


                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(activity,"user does not exists",Toast.LENGTH_SHORT).show()
                    }
                })



            }
            .setNeutralButton(resources.getString(R.string.negative)){dialog,which->
                Toast.makeText(activity,"Cancel Successful",Toast.LENGTH_SHORT).show()

            }.show()
    }


}