package com.rivaldo.pemesananonlinetokojagokupang.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang

@Dao
interface BarangDao {

    @Query("Select * from tabel_barang")
    fun getAllBarang(): LiveData<List<Barang>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(barang: Barang)

    @Query("delete from tabel_barang")
    suspend fun deleteAll()
}