package com.bbd.github_repo.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bbd.github_repo.R
import com.bbd.github_repo.databinding.ItemUserBinding
import com.bbd.github_repo.domain.entity.UserOverviewEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(
    private var list: List<UserOverviewEntity> = arrayListOf(),
    private val onItemClick: (UserOverviewEntity) -> Unit
) :
    RecyclerView.Adapter<UsersAdapter.UsersVh>() {

    private lateinit var context: Context

    class UsersVh(var itemBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersVh {
        context = parent.context
        return UsersVh(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: UsersVh, position: Int) {
        val item = list[position]

        holder.itemBinding.userNameTv.text = item.userName
        Glide.with(context).load(item.userAvatarUrl)
            .placeholder(R.drawable.ic_baseline_account_circle_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .override(100, 100)
            .into(holder.itemView.userAvatarIv)

        holder.itemBinding.itemCv.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount() = list.size

    fun updateList(newList: List<UserOverviewEntity>) {
        this.list = newList
        notifyDataSetChanged()
    }

}