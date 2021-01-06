package com.rivaldo.pemesananonlinetokojagokupang.repository

import androidx.lifecycle.LiveData
import com.rivaldo.pemesananonlinetokojagokupang.dao.KeranjangDao
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang

class KeranjangRepository(private val keranjangDao: KeranjangDao) {
    val allKeranjang: LiveData<List<Keranjang>> = keranjangDao.getAll()

 suspend fun insert(keranjang: Keranjang) {
        keranjangDao.insert(keranjang)
    }

   suspend fun getItem(id: Int) : Keranjang{
       return keranjangDao.getItem(id)
    }

    suspend fun deleteAll(){
        keranjangDao.deleteAll()
    }

}