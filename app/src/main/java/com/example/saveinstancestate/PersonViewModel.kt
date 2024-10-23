package com.example.saveinstancestate

import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PersonViewModel:ViewModel() {
    val adapter:MutableLiveData<MutableList<Person>> by lazy { MutableLiveData<MutableList<Person>>() }
}