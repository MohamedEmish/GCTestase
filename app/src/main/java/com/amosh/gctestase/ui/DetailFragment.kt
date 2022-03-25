package com.amosh.gctestase.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.amosh.gctestase.R
import com.amosh.gctestase.bases.BaseFragment
import com.amosh.gctestase.data.Car
import com.amosh.gctestase.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailsBinding>() {

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding
        get() = FragmentDetailsBinding::inflate

    private val args: DetailFragmentArgs by navArgs()

    override fun prepareView(savedInstanceState: Bundle?) {
        setData(args.car)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(car: Car?) {
        if (car == null)
            requireActivity().onBackPressed()

        with(binding) {
            tvBrand.text = car!!.brand
            tvModel.text = car.model?.toString()
            tvPalletNumber.text = car.plate_number
            tvPrice.text = "${car.unit_price} ${car.currency}"
            ivImage.imageTintList = ContextCompat.getColorStateList(requireContext(), car.color?.colorRes ?: R.color.gray)
            tvTitle.setOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }
}