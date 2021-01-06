package com.rivaldo.pemesananonlinetokojagokupang.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang

class Adapter(private val listBarang: List<Barang>) : RecyclerView.Adapter<AdapterViewHolder>() {
    private lateinit var listener : OnItemClickListener

    fun setOnClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val layoutview = LayoutInflater.from(parent.context).inflate(R.layout.barang_list, parent, false)
        return AdapterViewHolder(layoutview)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        if (position < listBarang.size) {
            val item = listBarang[position]
            holder.imageview.setImageResource(item.gambar)
            holder.namabarang.text = item.nama
            holder.hargabarang.text = item.harga.toString()
            holder.imgAddCart.setOnClickListener {
                listener.onAddCartClicked(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return listBarang.size
    }

    interface OnItemClickListener {
        fun onAddCartClicked(barang: Barang)
    }
}