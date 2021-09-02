package com.elephant.viewmodelexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType{
    PLUS, MINUS
}

//x 데이터의 변경
//x 뷰모델은 데이터의 변경사항을 알려주는 라이브 데이터를 가지고 있고
class MainViewModel :ViewModel(){
    //mutableLiveData - Editable
    //LiveData - not Editable

    //내부에서 설정하는 자료형은 mutable 로 변경 가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()
    val currentValue: LiveData<Int>
        get() = _currentValue

    init {
        Log.e("VIEW_MODEL", "생성자 호출")
        _currentValue.value = 0
    }

    fun updateValue(actionType: ActionType, input: Int){
        when(actionType){
            ActionType.PLUS -> _currentValue.value = _currentValue.value?.plus(input)
            ActionType.MINUS -> _currentValue.value = _currentValue.value?.minus(input)
        }
    }
}