package com.rivaldo.pemesananonlinetokojagokupang.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.*
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.db.TokoJagoRoomDatabase
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.repository.BarangRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class BarangViewModel(application: Application) : AndroidViewModel(application){
    private val repository : BarangRepository
    private val allBarang: LiveData<List<Barang>>


    init {
        val barangDao = TokoJagoRoomDatabase.getDatabase(application).barangDao()
        repository = BarangRepository(barangDao)
        allBarang = repository.allBarang
    }
//    fun insert(barang: Barang) =  {
//        repository.insert(barang)
//    }
    fun getAllBarang(): LiveData<List<Barang>>{
        return allBarang
    }




}



