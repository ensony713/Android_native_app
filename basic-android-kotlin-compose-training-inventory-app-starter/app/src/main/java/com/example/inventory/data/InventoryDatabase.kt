package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// entities = DB가 가지는 데이터 종류
// version = DB의 version. 테이블의 스키마를 변경할 때마다 번호를 높여야 함
// exportSchema = 버전 기록 백업을 유지할지(T) 말지(F)
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {

        // var은 캐시되지 않으며, rw가 모두 기본 메모리에서 이루어짐 = 모든 쓰레드에서 동일성 유지 가능
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it } // 최신 DB 인스턴스에 대한 참조 유지
            }
        }
    } // DB를 만들거나 가져오는 메소드에 대한 액세스를 허용, 클래스 이름을 한정자로 사용하는 객체

}