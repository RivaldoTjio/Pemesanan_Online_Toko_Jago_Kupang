package com.rivaldo.pemesananonlinetokojagokupang.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.pemesananonlinetokojagokupang.R

class KeranjangViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    var imageview: ImageView = itemview.findViewById(R.id.imgKeranjang)
    var namabarang: TextView = itemview.findViewById(R.id.txtBarangKeranjang)
    var kuantitas: TextView = itemview.findViewById(R.id.txtKuantitasKeranjang)

}