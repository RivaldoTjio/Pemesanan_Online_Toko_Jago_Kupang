package com.rivaldo.pemesananonlinetokojagokupang.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.BarangViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdapterKeranjang(private val listKeranjang : List<Keranjang>, private val listBarang: List<Barang>) : RecyclerView.Adapter<KeranjangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangViewHolder {
        val layoutview = LayoutInflater.from(parent.context).inflate(R.layout.keranjang_list, parent, false)
        return KeranjangViewHolder(layoutview)
    }

    override fun onBindViewHolder(holder: KeranjangViewHolder, position: Int) {
        if (position <= listKeranjang.size) {
            val item = listKeranjang[position]
            val barang = listBarang.find { it.id == item.idbarang }
            GlobalScope.launch(context = Dispatchers.Main) {
                if (barang != null) {
                    holder.imageview.setImageResource(barang.gambar)
                }
                if (barang != null) {
                    holder.namabarang.text = barang.nama
                }
                holder.kuantitas.text = "Qty : "+ item.kuantitas
            }

        }
    }

    override fun getItemCount(): Int {
       return  listKeranjang.size
    }

}