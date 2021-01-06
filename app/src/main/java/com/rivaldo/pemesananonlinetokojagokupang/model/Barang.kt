package com.rivaldo.pemesananonlinetokojagokupang.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_barang")
data class Barang(
        @PrimaryKey var id : Int,
        var nama : String?,
        var harga : Int,
        var gambar : Int
)