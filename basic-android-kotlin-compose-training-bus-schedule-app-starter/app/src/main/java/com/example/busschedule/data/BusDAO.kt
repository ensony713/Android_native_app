package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusDao {

    @Query("SELECT * FROM buses WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getByStopName(stopName: String): Flow<List<BusSchedule>>

    @Query("SELECT * FROM buses ORDER BY arrival_time ASC")
    fun getAllBuses(): Flow<List<BusSchedule>>
}