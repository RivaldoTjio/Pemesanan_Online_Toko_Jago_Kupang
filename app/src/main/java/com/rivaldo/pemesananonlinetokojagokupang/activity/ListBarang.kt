package com.rivaldo.pemesananonlinetokojagokupang.activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.adapter.Adapter
import com.rivaldo.pemesananonlinetokojagokupang.adapter.ProductGridItemDecoration
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityListBarangBinding
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityListBarangBinding.bind
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityListBarangBinding.inflate
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.BarangViewModel
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.KeranjangViewModel
import kotlinx.android.synthetic.main.activity_list_barang.*
import kotlinx.coroutines.*
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Executors.newSingleThreadExecutor

@InternalCoroutinesApi
public class ListBarang : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityListBarangBinding
    private lateinit var adapter: Adapter
    private lateinit var barangViewModel: BarangViewModel
    private lateinit var keranjangViewModel: KeranjangViewModel
    private lateinit var mToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.recyclerviewbarang.setHasFixedSize(true)
        supportActionBar?.title = "Daftar Barang"
        binding.recyclerviewbarang.layoutManager =GridLayoutManager(applicationContext, 2, RecyclerView.VERTICAL, false)
        val largePadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_large)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
        binding.recyclerviewbarang.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mToggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        //barangViewModel = ViewModelProviders.of(this).get(BarangViewModel::class.java)
       barangViewModel = ViewModelProvider(this@ListBarang, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(BarangViewModel::class.java)
        keranjangViewModel = ViewModelProvider(this@ListBarang, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(KeranjangViewModel::class.java)
        barangViewModel.getAllBarang().observe(this, Observer<List<Barang>> {
            this.adapter = Adapter(it)
            binding.recyclerviewbarang.adapter = this.adapter
            val largePadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_large)
            val smallPadding = resources.getDimensionPixelSize(R.dimen.grid_spacing_small)
            binding.recyclerviewbarang.addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

            adapter.setOnClickListener(object : Adapter.OnItemClickListener {
                override fun onAddCartClicked(barang: Barang) {
                    GlobalScope.launch(context = Dispatchers.IO) {
                        keranjangViewModel.insert(Keranjang(barang.id, 1))

                    }
                    Toast.makeText(this@ListBarang, barang.nama+"Ditambahkan", Toast.LENGTH_SHORT).show()

                }
            })
        })


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mToggle.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.daftarbarang -> {
                Toast.makeText(this, "Sudah di Daftar Barang", Toast.LENGTH_SHORT).show()
            }
            R.id.Keranjang -> {
                val intent = Intent(this, KeranjangActivity::class.java)
                startActivityForResult(intent,1)

            }
            R.id.Profile -> {
                val intent = Intent(this, DeveloperProfileActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }

}