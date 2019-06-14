package com.manis.gitapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.manis.gitapp.R
import com.manis.gitapp.model.GitModel
import kotlinx.android.synthetic.main.content_recycler_view.view.*

class GitAdapter(private val list: List<GitModel>?) : RecyclerView.Adapter<GitAdapter.GitViewHolder>() {

    var onItemClick : ((GitModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)
        return GitViewHolder(view)
    }

    override fun getItemCount(): Int = if(list.isNullOrEmpty()) 0 else list.size

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        holder.bindItems(list!![position])
    }

    inner class GitViewHolder(itemView:View) :
            RecyclerView.ViewHolder(itemView){

        fun bindItems(gitModel: GitModel){
            itemView.userName.text = gitModel.login
            Glide.with(itemView.context)
                    .load(gitModel.avatar_url)
                    .into(itemView.userAvatar)

            itemView.setOnClickListener {
                onItemClick?.invoke(gitModel)
            }
        }
    }
}