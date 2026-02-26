package dev.n3shemmy3.coffre.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.n3shemmy3.coffre.domain.model.Account
import dev.n3shemmy3.coffre.domain.model.Transaction
import dev.n3shemmy3.coffre.util.Converters

@Database(entities = [Transaction::class, Account::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transDao(): TransactionDao
    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "coffre-database"
                )
                    .apply {
                        enableMultiInstanceInvalidation()
                        fallbackToDestructiveMigrationOnDowngrade(false)
                    }
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}