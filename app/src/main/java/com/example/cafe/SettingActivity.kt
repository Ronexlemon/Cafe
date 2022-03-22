package com.example.cafe

import android.app.ProgressDialog
import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafe.data.Upload
import com.example.cafe.databinding.ActivitySettingBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ListResult
import com.google.firebase.storage.StorageReference

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    private lateinit var database:DatabaseReference
    private lateinit var storage : StorageReference
    private lateinit var list:ArrayList<Upload>
   // private lateinit var imageList:ArrayList<Upload>
  private   val imageList: ArrayList<Upload> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        list= arrayListOf<Upload>()

        initRecycler()
        getData()


    }


    private fun getData() {
        val progress = ProgressDialog(this)
        progress.setTitle("Loading ...")
        progress.show()
        database= FirebaseDatabase.getInstance().getReference("upload")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(dataSnapshot in snapshot.children){
                        val user = dataSnapshot.getValue(Upload::class.java)
                        list.add(user!!)
                    }
                    progress.dismiss()
                    binding.recyclerSet.adapter=DataAdapter(list)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SettingActivity,"database error",Toast.LENGTH_SHORT).show()
            }

        }

        )
    }

    private fun initRecycler() {
        binding.recyclerSet.layoutManager = LinearLayoutManager(this)
        binding.recyclerSet.setHasFixedSize(true)
    }
}