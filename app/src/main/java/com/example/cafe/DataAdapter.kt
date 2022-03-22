package com.example.cafe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.data.MyData
import com.example.cafe.data.Upload

import com.example.cafe.databinding.RecyclerUploadBinding
import com.squareup.picasso.Picasso

class DataAdapter(private val data:ArrayList<Upload>):RecyclerView.Adapter<MyViewHold>(){
    private lateinit var binding : RecyclerUploadBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHold {
        val layoutinflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutinflater, R.layout.recycler_upload,parent,false)

        return MyViewHold(binding)
    }

    override fun onBindViewHolder(holder: MyViewHold, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}
class MyViewHold(var binding: RecyclerUploadBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Upload) {
        binding.name.text = data.description
        binding.price.text = data.price
       Picasso.get().load(data.image).into(binding.image)



    }
}


