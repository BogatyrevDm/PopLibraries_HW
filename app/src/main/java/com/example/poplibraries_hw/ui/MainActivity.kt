package com.example.poplibraries_hw.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.poplibraries_hw.databinding.ActivityMainBinding
import com.example.poplibraries_hw.mvp.presenter.MainPresenter
import com.example.poplibraries_hw.mvp.view.MainView

class MainActivity : AppCompatActivity(), MainView {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    val presenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }
        binding.btnCounter1.setOnClickListener(listener)
        binding.btnCounter2.setOnClickListener(listener)
        binding.btnCounter3.setOnClickListener(listener)
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.btnCounter1.text = text
            1 -> binding.btnCounter2.text = text
            2 -> binding.btnCounter3.text = text
        }
    }
}