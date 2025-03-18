package com.example.ideaplatformtesttask.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ideaplatformtesttask.data.model.ItemCard
import com.example.ideaplatformtesttask.utilts.AppConstant.DATABASE_NAME
import com.example.ideaplatformtesttask.utilts.Converters

@Database(entities = [ItemCard::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ItemCardDatabase : RoomDatabase() {
    abstract fun itemCardDao(): ItemCardDao

    companion object {
        @Volatile
        private var INSTANCE: ItemCardDatabase? = null

        fun getDatabaseClient(context: Context): ItemCardDatabase {
            val dbFile = context.getDatabasePath(DATABASE_NAME)

            if (dbFile.exists()) {
                if (INSTANCE != null) return INSTANCE!!
                synchronized(this) {
                    INSTANCE = Room
                        .databaseBuilder(context, ItemCardDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    return INSTANCE!!
                }
            }
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ItemCardDatabase::class.java,
                    DATABASE_NAME
                )
                    .createFromAsset("database/$DATABASE_NAME.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}