package com.example.taipeizoointroduction.areaHome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoointroduction.MainNavigationDirections
import com.example.taipeizoointroduction.MainViewModel
import com.example.taipeizoointroduction.R
import com.example.taipeizoointroduction.databinding.FragmentAreaHomeBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class AreaHomeFragment : Fragment() {
    private var _binding: FragmentAreaHomeBinding? = null
    private val binding get() = _binding!!
    private val areaHomeViewModel by lazy {
        ViewModelProvider(this)[AreaHomeViewModel::class.java]
    }
    private val mainViewModel: MainViewModel by activityViewModels()
    private var myAreaHomeAdapter: AreaHomeAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.toolbarTitle.value = requireContext().getString(R.string.toolbar_home)
        initAdapter()
        areaHomeViewModel.areaOverview.observe(viewLifecycleOwner) {
            myAreaHomeAdapter?.submitList(it.results)
        }
    }

    private fun initAdapter() {
        binding.recyclerAreaHome.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            val decorator = MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            ).apply {
                isLastItemDecorated = false
                dividerColor = requireContext().getColor(R.color.silver)
                dividerThickness = 8
            }
            addItemDecoration(decorator)
            myAreaHomeAdapter = AreaHomeAdapter(
                AreaHomeAdapter.OnClickListener {
                    it.let {
                        findNavController().navigate(
                            MainNavigationDirections.actionHomeFragmentToDetailFragment(it)
                        )
                    }
                }
            )
            adapter = myAreaHomeAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}