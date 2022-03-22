package com.example.cafe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cafe.data.MyData
import com.example.cafe.databinding.RecyclerCustomBinding

class RecyclerAdapter( private val data:ArrayList<MyData>):RecyclerView.Adapter<MyViewHolder>() {
    private lateinit    var binding : RecyclerCustomBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutinflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(layoutinflater, R.layout.recycler_custom,parent,false)
return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return  data.size
    }
}
class MyViewHolder( var binding:RecyclerCustomBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(data:MyData){
        binding.username.text = data.username
        binding.email.text = data.email
        binding.password.text = data.password
    }
}