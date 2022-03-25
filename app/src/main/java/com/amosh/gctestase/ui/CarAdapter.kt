package com.amosh.gctestase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.amosh.gctestase.R
import com.amosh.gctestase.bases.BaseRecyclerAdapter
import com.amosh.gctestase.bases.BaseViewHolder
import com.amosh.gctestase.data.Car
import com.amosh.gctestase.databinding.RowCarItemLayoutBinding

class CarAdapter constructor(
    private val clickFunc: ((Car?) -> Unit)? = null,
) : BaseRecyclerAdapter<Car, RowCarItemLayoutBinding, CarAdapter.CarViewHolder>(
    MovieItemDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = RowCarItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CarViewHolder(binding = binding, click = clickFunc)
    }

    inner class CarViewHolder(
        private val binding: RowCarItemLayoutBinding,
        private val click: ((Car?) -> Unit)? = null
    ) : BaseViewHolder<Car, RowCarItemLayoutBinding>(binding) {

        override fun bind() {
            getRowItem()?.let {
                with(binding) {
                    root.setOnClickListener {
                        click?.invoke(getRowItem())
                    }

                    tvBrand.text = it.brand
                    tvModel.text = it.model?.toString()
                    tvPalletNumber.text = it.plate_number
                    ivImage.imageTintList = ContextCompat.getColorStateList(
                        root.context,
                        it.color?.colorRes ?: R.color.gray
                    )
                }
            }
        }
    }
}

class MovieItemDiffUtil : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem.color == newItem.color
    }

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
        return oldItem == newItem
    }
}