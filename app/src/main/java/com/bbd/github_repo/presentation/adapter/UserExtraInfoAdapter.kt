package com.bbd.github_repo.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbd.github_repo.databinding.ItemUserExtraInfoBinding
import com.bbd.github_repo.domain.entity.UserExtraInfo

class UserExtraInfoAdapter(private val list: List<UserExtraInfo>) :
    RecyclerView.Adapter<UserExtraInfoAdapter.UserExtraInfoVh>() {

    private lateinit var context: Context

    class UserExtraInfoVh(var itemBinding: ItemUserExtraInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserExtraInfoVh {
        context = parent.context
        return UserExtraInfoVh(
            ItemUserExtraInfoBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserExtraInfoVh, position: Int) {
        val info = list[position]
        holder.itemBinding.infoTv.text = info.text
        holder.itemBinding.infoIv.setImageResource(info.resourceId)
    }

    override fun getItemCount() = list.size

}