package br.com.fiap.protege.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.protege.model.Denuncia

@Database(entities = [Denuncia::class], version = 1)
abstract class DenunciaDB : RoomDatabase() {

    abstract fun denunciaDao(): DenunciaDao

    companion object {

        private lateinit var instance: DenunciaDB

        fun getDatabase(context: Context): DenunciaDB {
            if (!::instance.isInitialized) {
                instance = Room
                    .databaseBuilder(
                        context,
                        DenunciaDB::class.java,
                        "denuncia_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}