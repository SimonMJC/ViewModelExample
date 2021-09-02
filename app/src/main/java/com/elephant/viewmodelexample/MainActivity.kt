package com.elephant.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elephant.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.currentValue.observe(this, {
            Log.e("Observer", "cValue - liveData Change -> $it ")
            binding.numberTextView.text = it.toString()
        })
//        binding.plusButton.setOnClickListener(this)
        binding.plusButton.setOnClickListener {
        val userInput = binding.userInput.text.toString().toInt()
                mainViewModel.updateValue(actionType = ActionType.PLUS, userInput)
        }
//        binding.minusButton.setOnClickListener(this)
        binding.minusButton.setOnClickListener{
            val userInput = binding.userInput.text.toString().toInt()
                mainViewModel.updateValue(actionType = ActionType.MINUS, userInput)
        }
    }

    override fun onClick(v: View?) {
//        when(v.findViewById<Button>(R.id)){
//            binding.plusButton ->{
//            }
//            binding.minusButton ->{
//            }
//        }
    }
}