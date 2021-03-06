package com.rivaldo.pemesananonlinetokojagokupang.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.lifecycle.ViewModelProvider
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityLoginBinding
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityLoginBinding.inflate
import com.rivaldo.pemesananonlinetokojagokupang.db.TokoJagoRoomDatabase
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.viewmodel.BarangViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@InternalCoroutinesApi
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var barangViewModel: BarangViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        barangViewModel = ViewModelProvider(this@LoginActivity, ViewModelProvider.AndroidViewModelFactory.getInstance(this.application)).get(BarangViewModel::class.java)
        GlobalScope.launch(context = Dispatchers.IO) {
            barangViewModel.insert(Barang(1, "Pepsodent 80 gr", 7000, R.drawable.pepsodent))
            barangViewModel.insert(Barang(2, "Lifebuoy 225ml", 18000, R.drawable.sabuncairlifebuoy))
            barangViewModel.insert(Barang(3,"Royco Ayam 100gr", 5000, R.drawable.roycoayam100g))
            barangViewModel.insert(Barang(4, "Lifebuoy Sampo 340 ml", 15000, R.drawable.lifebuoyshampo))
            barangViewModel.insert(Barang(5, "Teh Pucuk Harum 300ml", 3000, R.drawable.tehpucuk))
            barangViewModel.insert(Barang(6, "Walls Cornetto Oreo ", 10000, R.drawable.cornetto))
            barangViewModel.insert(Barang(7,"Hand Body Nivea 340ml", 20000, R.drawable.handbodynivea))
            barangViewModel.insert(Barang(8, "Silverqueen 65 gr", 14000, R.drawable.silverqueen))
            barangViewModel.insert(Barang(9, "Ponds Men 100 ml", 28000, R.drawable.pondsmensabunmuka))
            barangViewModel.insert(Barang(10, "Clean and Clear Face Wash 100ml", 10000, R.drawable.cleanandclear))
        }

        binding.btnLogin.setOnClickListener {
            with(binding){
                if (!isCredentialValid(editTextPassword.text!!)) {
                        textInputLayoutPassword.error = "Password Must Contain At Least 8 Character"
                }
                else {
                    textInputLayoutPassword.error = null
                    //create intent
                    val intent = Intent(this@LoginActivity, ListBarang::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    private fun isCredentialValid(text: Editable?) : Boolean{
        return text != null && text.length >= 8
    }
}