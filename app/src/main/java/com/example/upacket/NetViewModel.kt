package com.example.upacket

import androidx.lifecycle.MutableLiveData

class NetViewModel : BaseViewModel() {
    var fictions = MutableLiveData<List<Fiction>>()
    val model = NetModel()

    fun getFictions() {
        asyncRequest {
            val data = model.getData()
            fictions.value = data
        }

    }
}
