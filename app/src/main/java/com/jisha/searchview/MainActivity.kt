package com.jisha.searchview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.jisha.adapter.LanguageAdapter
import com.jisha.modelclass.LanguageData
import com.jisha.searchview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val languageAdapter: LanguageAdapter by lazy { LanguageAdapter() }
    private var binding: ActivityMainBinding? = null
    private val mList = mutableListOf<LanguageData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding?.root)
        init()
        initViews()
    }



    private fun init(){
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            adapter = languageAdapter
            addDataToList()
        }

    }
    private fun initViews() {
        binding?.searchView?.doOnTextChanged { text, start, before, count ->
            languageAdapter.filter.filter(text)
        }
     }



    private fun addDataToList() {
        val subList = listOf(
            LanguageData("Java", R.drawable.java),
            LanguageData("Kotlin", R.drawable.java),
            LanguageData("Python", R.drawable.java),
            LanguageData("C++", R.drawable.java),
            LanguageData("PHP", R.drawable.java),
            LanguageData("Ruby", R.drawable.java),
            LanguageData("R", R.drawable.java),
            LanguageData("JavaScript", R.drawable.java)
        )
        languageAdapter.updateList(subList)
    }

    fun logThis(message: Any?) {
        Log.d("logThis","----> $message")
    }

}