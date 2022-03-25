package com.amosh.gctestase.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.amosh.gctestase.bases.BaseFragment
import com.amosh.gctestase.data.AppResult
import com.amosh.gctestase.data.FilterBody
import com.amosh.gctestase.databinding.FragmentMainBinding
import com.amosh.gctestase.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("NotifyDataSetChanged")
@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by activityViewModels()
    private var filterSheet: FilterSheetFragment? = null
    private var filterBody = FilterBody()

    private val adapter: CarAdapter by lazy {
        CarAdapter { car ->
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment().setCar(car)
            findNavController().navigate(action)
        }
    }

    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
        get() = FragmentMainBinding::inflate

    override fun prepareView(savedInstanceState: Bundle?) {
        binding.rvCars.adapter = adapter
        initObservers()
        getCarsList()

        binding.ivFilter.setOnClickListener {
            filterSheet =
                FilterSheetFragment.newInstance(
                    object : FilterSheetFragment.OnActionsListener {
                        override fun onDoneListener(fb: FilterBody) {
                            filterBody = fb
                            getCarsList()
                            filterSheet?.dismiss()
                        }
                    },
                    filterBody
                )
            filterSheet?.show(parentFragmentManager, FilterSheetFragment.TAG)
        }
    }

    private fun getCarsList() =
        viewModel.setEvent(MainViewModel.MainEvent.GetListOfCare(filterBody = filterBody))

    /**
     * Initialize Observers
     */
    private fun initObservers() {
        with(viewModel) {
            carsList.observe(viewLifecycleOwner) {
                when (it) {
                    is AppResult.Loading -> Unit
                    is AppResult.SuccessWithEmptyList -> {
                        val data = it.cars
                        binding.emptyState.isVisible = data.isNullOrEmpty()
                        binding.tvEmptyMessage.text = it.status?.message
                        adapter.submitList(mutableListOf())
                        adapter.notifyDataSetChanged()
                        filterSheet?.dismiss()
                    }
                    is AppResult.SuccessWithList -> {
                        val data = it.cars
                        binding.emptyState.isVisible = data.isNullOrEmpty()
                        adapter.submitList(data)
                        adapter.notifyDataSetChanged()
                        filterSheet?.dismiss()
                    }
                }
            }
        }
    }

}