package com.omer.retrofitapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omer.retrofitapp.R
import com.omer.retrofitapp.model.PostModel
import com.squareup.picasso.Picasso


class PostRecyclerAdapter(val postList:ArrayList<PostModel>) : RecyclerView.Adapter<PostRecyclerAdapter.PostViewHolder>(){


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val username : TextView = itemView.findViewById<TextView?>(R.id.textViewUserName)
        val userImage : ImageView = itemView.findViewById<ImageView?>(R.id.userImage)
        val postImage : ImageView = itemView.findViewById<ImageView?>(R.id.postImage)
        val postStatement : TextView = itemView.findViewById<TextView?>(R.id.textViewPostStatement)


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_recycler_row,parent,false) // ViewHolder için bağlanacak görüntüyü tanımladık
        return  PostViewHolder(itemView) // viewHolder döndürdük --> Recycler View ile Holder bağlandı

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = postList[position]

        holder.username.text = currentItem.userName
        holder.postStatement.text =  currentItem.postStatement
        Picasso.get().load(currentItem.userImage).into(holder.userImage)
        Picasso.get().load(currentItem.postImage).into(holder.postImage)
    }
    override fun getItemCount(): Int {
        // rcycler View içerisinde kaç tane satır olacak
        // (val kitapGorselListesi : ArrayList<Bitmap>) ==> bu listenin eleman sayısına ıulaşmak için constructor da listeyi aldık
        return postList.size
    }


}