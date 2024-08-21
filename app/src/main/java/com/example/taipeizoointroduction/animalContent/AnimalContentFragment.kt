package com.example.taipeizoointroduction.animalContent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.taipeizoointroduction.MainViewModel
import com.example.taipeizoointroduction.R
import com.example.taipeizoointroduction.data.animal.ResultX
import com.example.taipeizoointroduction.databinding.FragmentAnimalContentBinding
import com.example.taipeizoointroduction.databinding.FragmentAreaDetailBinding
import com.example.taipeizoointroduction.utils.loadImage

class AnimalContentFragment : Fragment() {
    private var _binding: FragmentAnimalContentBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimalContentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedAnimalKey =
            AnimalContentFragmentArgs.fromBundle(requireArguments()).selectedAnimalKey
        if (selectedAnimalKey != null) {
            mainViewModel.toolbarTitle.value = selectedAnimalKey.a_name_ch
            loadImage(binding.imgAnimalPic, selectedAnimalKey.a_pic01_url)
            binding.tvAnimalContent.text = formatAnimalContent(selectedAnimalKey)
        } else {
            mainViewModel.toolbarTitle.value = ""
            binding.imgAnimalPic.visibility = View.GONE
            binding.tvAnimalContent.text = getString(R.string.argument_not_found)
        }
    }

    private fun formatAnimalContent(selectedAnimalKey: ResultX): String {
        val nameParts = listOfNotNull(
            selectedAnimalKey.a_name_ch.takeIf { it.isNotEmpty() },
            selectedAnimalKey.a_name_en.takeIf { it.isNotEmpty() }
        ).joinToString(separator = "\n")

        val detailParts = listOfNotNull(
            selectedAnimalKey.a_alsoknown.takeIf { it.isNotEmpty() }?.let { "別名：\n$it" },
            selectedAnimalKey.a_distribution.takeIf { it.isNotEmpty() }?.let { "簡介：\n$it" },
            selectedAnimalKey.a_feature.takeIf { it.isNotEmpty() }?.let { "辨認方式：\n$it" },
            selectedAnimalKey.a_behavior.takeIf { it.isNotEmpty() }?.let { "功能性：\n$it" },
            selectedAnimalKey.a_update.takeIf { it.isNotEmpty() }?.let { "最後更新：\n$it" }
        ).joinToString(separator = "\n\n")

        return listOf(nameParts, detailParts)
            .filter { it.isNotEmpty() }
            .joinToString(separator = "\n\n")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}