package com.rivaldo.pemesananonlinetokojagokupang.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.databinding.BarangListBinding

class AdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageview: ImageView = itemView.findViewById(R.id.imgBarang)
    var namabarang : TextView = itemView.findViewById(R.id.txtNamaBarang)
    var hargabarang : TextView = itemView.findViewById(R.id.txtHarga)
    var imgAddCart : ImageView = itemView.findViewById(R.id.imgAddCart)
}
