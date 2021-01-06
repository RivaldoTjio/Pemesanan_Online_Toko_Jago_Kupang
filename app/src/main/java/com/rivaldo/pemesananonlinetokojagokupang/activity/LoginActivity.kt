package com.rivaldo.pemesananonlinetokojagokupang.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityLoginBinding
import com.rivaldo.pemesananonlinetokojagokupang.databinding.ActivityLoginBinding.inflate
import com.rivaldo.pemesananonlinetokojagokupang.db.TokoJagoRoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        executor.execute {
            TokoJagoRoomDatabase.getDatabase(application)
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