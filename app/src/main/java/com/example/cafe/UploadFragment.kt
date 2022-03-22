package com.example.cafe

import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.cafe.data.Upload
import com.example.cafe.databinding.FragmentUploadBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*
import kotlin.collections.HashMap


class UploadFragment : Fragment() {
    private lateinit var binding : FragmentUploadBinding
    private lateinit var database:DatabaseReference
  private   var imageCode= 100
    private lateinit var imageuri :Uri
    private lateinit var  storage: StorageReference


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_upload,container,false)


binding.save.setOnClickListener {

    val description = binding.description.text.toString()
    val price = "Ksh "+" "+ binding.price.text.toString()
    binding.description.text.clear()
    binding.price.text.clear()

  //  addDescription(description)
    uploadToDatabase(description,price)
}
        binding.upload.setOnClickListener {
            uploadImage()
            binding.save.visibility = View.VISIBLE
            binding.upload.visibility = View.GONE

        }



        return binding.root
    }



    private fun uploadToDatabase(description:String,price:String) {
        if (imageuri != null) {
            val progress = ProgressDialog(activity)
            progress.setTitle("uploading ......")
            progress.show()

  storage = FirebaseStorage.getInstance().getReference("upload/"+ UUID.randomUUID().toString())
    storage.putFile(imageuri)
        .addOnSuccessListener{
            progress.dismiss()
            Toast.makeText(activity,"success added",Toast.LENGTH_SHORT).show()
            storage.downloadUrl.addOnSuccessListener {

                database = FirebaseDatabase.getInstance().getReference("upload")

                val data = Upload(description,price, it.toString())
                database.child(description).setValue(data)

            } }
        .addOnFailureListener{
            Toast.makeText(activity,"failed to upload",Toast.LENGTH_SHORT).show()
        }


        }
        else{
            Toast.makeText(activity,"Please provide an Image",Toast.LENGTH_SHORT).show()
        }






        }


    private fun uploadImage() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,imageCode)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==imageCode && resultCode == RESULT_OK){
  imageuri= data?.data!!
             binding.image.setImageURI(imageuri)
        }
    }


}