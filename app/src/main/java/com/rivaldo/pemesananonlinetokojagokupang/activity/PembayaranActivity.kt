package com.rivaldo.pemesananonlinetokojagokupang.activity

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.navigation.NavigationView
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityPembayaranBinding.inflate
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityPembayaranBinding
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityPembayaranBinding.bind
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.KeranjangViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class PembayaranActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    val listPembayaran = arrayListOf<String>("Tunai", "Transfer Bank")
    private lateinit var binding: ActivityPembayaranBinding
    private lateinit var mToggle: ActionBarDrawerToggle
    @InternalCoroutinesApi
    private lateinit var keranjangViewModel: KeranjangViewModel
    private lateinit var metodepembayaran : String
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.title = "Metode Pembayaran"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        keranjangViewModel = ViewModelProvider(this@PembayaranActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(KeranjangViewModel::class.java)
        mToggle = ActionBarDrawerToggle(this, binding.drawerLayoutPembayaran, R.string.open, R.string.close)
        binding.drawerLayoutPembayaran.addDrawerListener(mToggle)
        mToggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)
        binding.rbTunai.setOnClickListener {setPayment("Tunai")    }
        binding.rbTransfer.setOnClickListener {setPayment("Transfer")     }
        binding.rbGopay.setOnClickListener {setPayment("Gopay")    }
        binding.btnPembayaran.setOnClickListener {
            alertClick()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return mToggle.onOptionsItemSelected(item)
    }


    private fun onPaymentSelected() {

    }

    public fun alertClick(){
        if (metodepembayaran.isNotEmpty()){
        AlertDialog.Builder(this)
                .setMessage("Pembayaran Berhasil ! ")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, i ->
                })
                .show()
        GlobalScope.launch(context = Dispatchers.IO){
            keranjangViewModel.deleteAll()
            finish()
        }

        }else{
            AlertDialog.Builder(this)
                .setMessage("Pembayaran Gagal !")
                .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, i ->
                })
                .show()
        }
    }

    @InternalCoroutinesApi
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

    fun setPayment(payment: String) {
        this.metodepembayaran = payment
    }

}