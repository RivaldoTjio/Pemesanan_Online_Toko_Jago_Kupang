package com.rivaldo.pemesananonlinetokojagokupang.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.rivaldo.pemesananonlinetokojagokupang.db.TokoJagoRoomDatabase
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang
import com.rivaldo.pemesananonlinetokojagokupang.repository.KeranjangRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.coroutineScope

@InternalCoroutinesApi
class KeranjangViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : KeranjangRepository
    private val allKeranjangItem: LiveData<List<Keranjang>>

    init {
        val keranjangDao = TokoJagoRoomDatabase.getDatabase(application).keranjangDao()
        repository = KeranjangRepository(keranjangDao)
        allKeranjangItem = repository.allKeranjang
    }

   suspend fun insert(keranjang: Keranjang){
       val item = repository.getItem(keranjang.idbarang)
       if (item != null){
           keranjang.kuantitas = keranjang.kuantitas + item.kuantitas
       }
        repository.insert(keranjang)
    }

    fun getAllItem(): LiveData<List<Keranjang>>{
        return allKeranjangItem
    }

   suspend fun deleteAll() {
        repository.deleteAll()
    }
}