package com.elephant.viewmodelexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.elephant.viewmodelexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var userInput: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        binding.main = this
        binding.lifecycleOwner = this

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.currentValue.observe(this, {
            Log.e("Observer", "cValue - liveData Change -> $it ")
            binding.numberTextView.text = it.toString()
            mainViewModel.updateText(it)
            binding.invalidateAll()
        })
    }

    fun plusClick(v: View?){
        if (binding.userInput.text.toString() != "0" && binding.userInput.text.toString() != "") {
            userInput = binding.userInput.text.toString().toInt()
            mainViewModel.updateValue(actionType = ActionType.PLUS, userInput)
        }
    }

    fun minusClick(v: View?){
        if (!(binding.userInput.text.toString() == "0" || binding.userInput.text.toString() == "")) {
            userInput = binding.userInput.text.toString().toInt()
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