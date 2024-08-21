package com.example.taipeizoointroduction.areaDeatil

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taipeizoointroduction.MainNavigationDirections
import com.example.taipeizoointroduction.MainViewModel
import com.example.taipeizoointroduction.R
import com.example.taipeizoointroduction.data.animal.ResultX
import com.example.taipeizoointroduction.databinding.FragmentAreaDetailBinding

class AreaDetailFragment : Fragment() {

    private var _binding: FragmentAreaDetailBinding? = null
    private val binding get() = _binding!!
    private val animalDetailViewModel by lazy {
        ViewModelProvider(this)[AreaDetailViewModel::class.java]
    }
    private var myAreaDetailAdapter: AreaDetailAdapter? = null
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAreaDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedAreaKey = AreaDetailFragmentArgs.fromBundle(requireArguments()).selectedAreaKey
        if (selectedAreaKey != null) {
            animalDetailViewModel.selectedArea = selectedAreaKey
            mainViewModel.toolbarTitle.value = animalDetailViewModel.selectedArea?.e_name
            initAdapter()
            animalDetailViewModel.formattedExhibitInfo.observe(viewLifecycleOwner) {
                it?.let {
                    myAreaDetailAdapter?.submitList(it)
                }
            }
        } else {
            mainViewModel.toolbarTitle.value = ""
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = getString(R.string.argument_not_found)
        }
    }

    private fun initAdapter() {
        binding.recyclerAreaDetail.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            myAreaDetailAdapter = AreaDetailAdapter(
                AreaDetailAdapter.OnClickListener { item ->
                    handItemClick(item)
                }
            )
            adapter = myAreaDetailAdapter
        }
    }

    private fun handItemClick(item: HashMap<String, Any>) {
        item.forEach { (key, value) ->
            when (key) {
                "animal" ->
                    findNavController().navigate(
                        MainNavigationDirections.actionDetailFragmentToContentFragment(value as? ResultX)
                    )

                "link" -> {
                    val url = value.toString()
                    val uri =
                        Uri.parse(url)
                    if (uri.isAbsolute) {
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            requireContext().getString(R.string.detail_page_url_error),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}