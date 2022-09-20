package com.test.mornhouse.ui.main.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.test.mornhouse.R
import com.test.mornhouse.model.Fact
import kotlinx.android.synthetic.main.item_fact.view.*

class FactAdapter : BaseQuickAdapter<Fact, BaseViewHolder>(R.layout.item_fact){

    var onItemClickListener: ((position: Int, id: Long) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: Fact) {
        holder.itemView.tv_text_fact_item_fact.text = item.text
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(holder.adapterPosition, item.id!!)
        }
    }

    fun getFact(position: Int): Fact? {
        return getItem(position)
    }
}