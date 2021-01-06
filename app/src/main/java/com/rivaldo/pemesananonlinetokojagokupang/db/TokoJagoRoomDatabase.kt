package com.rivaldo.pemesananonlinetokojagokupang.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.rivaldo.pemesananonlinetokojagokupang.R
import com.rivaldo.pemesananonlinetokojagokupang.dao.BarangDao
import com.rivaldo.pemesananonlinetokojagokupang.dao.KeranjangDao
import com.rivaldo.pemesananonlinetokojagokupang.model.Barang
import com.rivaldo.pemesananonlinetokojagokupang.model.Keranjang
import kotlinx.coroutines.*
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(Barang::class,Keranjang::class), version = 1, exportSchema = false)
public abstract class TokoJagoRoomDatabase : RoomDatabase(){
    abstract fun barangDao() : BarangDao
    abstract fun keranjangDao() : KeranjangDao

    companion object{
        @Volatile
        private var INSTANCE: TokoJagoRoomDatabase? = null

        @InternalCoroutinesApi
       fun getDatabase(context: Context): TokoJagoRoomDatabase {
                val tempInstance = INSTANCE

                synchronized(this) {
                    val instance = Room.databaseBuilder(
                            context.applicationContext,
                            TokoJagoRoomDatabase::class.java,
                            "toko_jago_database"
                    )       .addCallback(roomCallback)
                            .build()
                    INSTANCE = instance
                    return instance
                }

        }
        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                GlobalScope.launch(context = Dispatchers.IO) {
                    PopulateDB(INSTANCE).initialize()
                }


            }
        }
    }


class PopulateDB(db: TokoJagoRoomDatabase?)  {
    private val barangDao = db?.barangDao()

    suspend fun initialize() {
        barangDao?.insert(Barang(1, "Pepsodent 80 gr", 7000, R.drawable.pepsodent))
    }



}
}