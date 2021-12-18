package com.elephant.viewmodelexample

import android.util.Log
import android.widget.Toast
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class ActionType {
    PLUS, MINUS
}

//x 데이터의 변경
//x 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MainViewModel : ViewModel() {
    //mutableLiveData - Editable
    //LiveData - not Editable

    //내부에서 설정하는 자료형은 mutable 로 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()
    val currentValue: LiveData<Int>
        get() = _currentValue
    private val _checkValue = MutableLiveData<Int>()
    val hundredValue: LiveData<Int>
        get() = _checkValue

    init {
        Log.e("VIEW_MODEL", "생성자 호출")
        _currentValue.value = 0
        _checkValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int) {
        viewModelScope.launch { //x viewModel 에서는 코루틴 사용
            try { //x 코루틴 사용 시 예외처리에 주의
                when (actionType) {
                    ActionType.PLUS -> _currentValue.value = _currentValue.value?.plus(input)
                    ActionType.MINUS -> _currentValue.value = _currentValue.value?.minus(input)
                }
            }catch(e: Throwable){
              e.printStackTrace()
                Log.e("THROWABLE","${e.message}")
            }
        }
    }
    
    fun updateText(input: Int){
        viewModelScope.launch {
            try {
              when(input){
                50 ->_checkValue.value = Log.e("50?", input.toString())
                else -> Log.e("currentNumber", input.toString())
              }
            }catch(e: Throwable){
                e.printStackTrace()
                Log.e("Throwable", e.message.toString())
            }
        }
    }
}