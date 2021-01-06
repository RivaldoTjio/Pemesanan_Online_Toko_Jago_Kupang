package com.rivaldo.pemesananonlinetokojagokupang.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tabel_keranjang")
data class Keranjang(
    @PrimaryKey var idbarang : Int,
    var kuantitas : Int
)
