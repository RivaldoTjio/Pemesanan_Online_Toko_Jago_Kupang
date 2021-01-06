package com.rivaldo.pemesananonlinetokojagokupang.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.adapter.Adapter
import com.rivaldo.pemesananonlinetokojagokupang.adapter.AdapterKeranjang
import com.rivaldo.pemesananonlinetokojagokupang.adapter.KeranjangViewHolder
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityKeranjangBinding
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityKeranjangBinding.bind
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityKeranjangBinding.inflate
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.BarangViewModel
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.KeranjangViewModel
import kotlinx.coroutines.InternalCoroutinesApi
@InternalCoroutinesApi
class KeranjangActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityKeranjangBinding
    private lateinit var adapter: AdapterKeranjang
    private lateinit var keranjangViewModel: KeranjangViewModel
    private lateinit var barangViewModel: BarangViewModel
    private lateinit var allbarang: List<Barang>
    private lateinit var mToggle: ActionBarDrawerToggle

    companion object{
        const val PEMBAYARAN = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerviewkeranjang.setHasFixedSize(true)
        supportActionBar?.title = "Keranjang"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToggle = ActionBarDrawerToggle(this, binding.drawerLayoutKeranjang, R.string.open, R.string.close)
        binding.drawerLayoutKeranjang.addDrawerListener(mToggle)
        mToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        binding.recyclerviewkeranjang.layoutManager = LinearLayoutManager(this)
        keranjangViewModel = ViewModelProvider(this@KeranjangActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(KeranjangViewModel::class.java)
        barangViewModel = ViewModelProvider(this@KeranjangActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(BarangViewModel::class.java)
        barangViewModel.getAllBarang().observe(this, Observer<List<Barang>> {
            allbarang = it
            if (allbarang.isEmpty()){
                binding.recyclerviewkeranjang.visibility = View.GONE
                binding.kosong.visibility = View.VISIBLE
            }

        keranjangViewModel.getAllItem().observe(this, Observer<List<Keranjang>> {
            adapter = AdapterKeranjang(it, allbarang)
            binding.recyclerviewkeranjang.adapter = adapter

        })

        })

        binding.btnBayar.setOnClickListener {
            val intent = Intent(this, PembayaranActivity::class.java)
            startActivityForResult(intent, PEMBAYARAN)
        }
    }

  

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mToggle.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        adapter.notifyDataSetChanged()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.daftarbarang -> {
               val intent = Intent(this, ListBarang::class.java)
                startActivity(intent)
            }
            R.id.Keranjang -> {
                val intent = Intent(this, KeranjangActivity::class.java)
                startActivity(intent)

            }

        }
        return true
    }
}