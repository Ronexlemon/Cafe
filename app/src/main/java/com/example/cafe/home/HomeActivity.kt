package com.example.cafe.home

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe.R
import com.example.cafe.RecyclerAdapter
import com.example.cafe.data.MyData
import com.example.cafe.databinding.ActivityHomeBinding
import com.google.firebase.database.*

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var recyclerAdapt : RecyclerAdapter
    private lateinit var database: DatabaseReference
    private lateinit var list:ArrayList<MyData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list = arrayListOf<MyData>()

        inintRecycler()
        getUser()
    }
    private fun getUser(){
        Log.i("TAg","getUser initiate")
        val progress = ProgressDialog(this)
        progress.setTitle("retrieving .....")
        progress.show()
      database =FirebaseDatabase.getInstance().getReference("User")
        database.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    progress.dismiss()
                   for(userSnapshot in snapshot.children ){
                        val results = userSnapshot.getValue(MyData::class.java)
                        list.add(results!!)

                    }

                    binding.recycler.adapter=RecyclerAdapter(list)
                    Toast.makeText(this@HomeActivity,"found database",Toast.LENGTH_SHORT).show()

                }
                else{
                    Toast.makeText(this@HomeActivity,"found not database",Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@HomeActivity,error.message,Toast.LENGTH_SHORT).show()
            }


        })
    }

     private fun inintRecycler() {
         binding.recycler.layoutManager = LinearLayoutManager(this)
         binding.recycler.setHasFixedSize(true)
         Log.i("Tag","recycler initiate")
     }

}