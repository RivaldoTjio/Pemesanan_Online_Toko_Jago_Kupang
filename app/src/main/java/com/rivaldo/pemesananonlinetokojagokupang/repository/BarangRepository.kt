package com.rivaldo.pemesananonlinetokojagokupang.repository

import androidx.lifecycle.LiveData
import com.rivaldo.pemesananonlinetokojagokupang.dao.BarangDao
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang

class BarangRepository(private val barangDao : BarangDao) {
    val allBarang: LiveData<List<Barang>> = barangDao.getAllBarang()

    suspend fun insert(barang: Barang){
        barangDao.insert(barang)
    }
}