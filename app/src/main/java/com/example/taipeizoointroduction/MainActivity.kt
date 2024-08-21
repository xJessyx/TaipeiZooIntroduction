package com.example.taipeizoointroduction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoointroduction.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.silver)
        mainViewModel.toolbarTitle.observe(this) {
            it?.let {
                updateToolbar(it)
            }
        }
    }

    private fun updateToolbar(text: String) {
        if (text.contains(this.getString(R.string.toolbar_home))) {
            binding.tvToolbar.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_menu_24,
                0,
                0,
                0
            )
        } else {
            binding.tvToolbar.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_arrow_back_24,
                0,
                0,
                0
            )
            binding.tvToolbar.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }
        }
        binding.tvToolbar.text = text
    }
}