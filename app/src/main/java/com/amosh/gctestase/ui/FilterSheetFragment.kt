package com.amosh.gctestase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.amosh.gctestase.data.CarColor
import com.amosh.gctestase.data.FilterBody
import com.amosh.gctestase.databinding.FragmentFilterSheetBinding
import com.amosh.gctestase.utils.ScreenUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterSheetBinding? = null
    private val binding get() = _binding!!

    private var listener: OnActionsListener? = null
    private var filterBody: FilterBody = FilterBody()

    override fun onStart() {
        super.onStart()
        // To show the sheet full height
        val dialog = dialog
        if (dialog != null) {
            val bottomSheet = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        val view = view
        view?.post {
            val parent = view.parent as View
            val params: CoordinatorLayout.LayoutParams =
                parent.layoutParams as CoordinatorLayout.LayoutParams
            val behavior = params.behavior
            val bottomSheetBehavior = behavior as BottomSheetBehavior
            bottomSheetBehavior.peekHeight = ScreenUtils(requireContext()).height
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            etPrice.setText(filterBody.price?.toString() ?: "")
            rbAny.isChecked = filterBody.color == null
            rbBlack.isChecked = filterBody.color == CarColor.BLACK
            rbBlue.isChecked = filterBody.color == CarColor.BLUE
            rbRed.isChecked = filterBody.color == CarColor.RED
            rbWhite.isChecked = filterBody.color == CarColor.WHITE

            btnDone.setOnClickListener {
                when {
                    rbAny.isChecked -> filterBody.color = null
                    rbBlack.isChecked -> filterBody.color = CarColor.BLACK
                    rbBlue.isChecked -> filterBody.color = CarColor.BLUE
                    rbWhite.isChecked -> filterBody.color = CarColor.WHITE
                    rbRed.isChecked -> filterBody.color = CarColor.RED
                }
                
                filterBody.price = etPrice.text?.toString()?.toDoubleOrNull()

                listener?.onDoneListener(
                    filterBody
                )
            }
        }
    }

    interface OnActionsListener {
        fun onDoneListener(fb: FilterBody)
    }

    companion object {
        const val TAG = "FilterSheetFragment"

        @JvmStatic
        fun newInstance(
            listener: OnActionsListener?,
            filterBody: FilterBody?,
        ): FilterSheetFragment {
            val fragment = FilterSheetFragment()
            fragment.listener = listener
            fragment.filterBody = filterBody ?: FilterBody()
            return fragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}