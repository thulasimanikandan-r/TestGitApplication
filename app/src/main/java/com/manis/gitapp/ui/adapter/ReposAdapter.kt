package com.manis.gitapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manis.gitapp.R
import com.manis.gitapp.model.ReposModel
import kotlinx.android.synthetic.main.content_recycler_view.view.*

class ReposAdapter(private val list: List<ReposModel>?) : RecyclerView.Adapter<ReposAdapter.ReposViewHolder>() {

    var onItemClick : ((ReposModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.content_recycler_view, parent, false)
        return ReposViewHolder(view)
    }

    override fun getItemCount(): Int = if(list.isNullOrEmpty()) 0 else list.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.bindItems(list!![position])
    }

    inner class ReposViewHolder(itemView:View) :
            RecyclerView.ViewHolder(itemView){

        fun bindItems(repoModel: ReposModel){
            itemView.userName.text = repoModel.name
            itemView.userAvatar.visibility = View.GONE
            itemView.setOnClickListener {
                onItemClick?.invoke(repoModel)
            }
        }
    }
}