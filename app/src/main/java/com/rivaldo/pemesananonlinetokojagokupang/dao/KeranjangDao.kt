package com.rivaldo.pemesananonlinetokojagokupang.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang

@Dao
interface KeranjangDao {

    @Query("select * from tabel_keranjang")
    fun getAll(): LiveData<List<Keranjang>>

    @Query("select * from tabel_keranjang where idbarang = :id")
   suspend fun getItem(id : Int): Keranjang

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Keranjang)

    @Query("delete from tabel_keranjang")
   suspend fun deleteAll()


}