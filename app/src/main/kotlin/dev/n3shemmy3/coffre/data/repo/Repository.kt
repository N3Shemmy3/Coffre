package dev.n3shemmy3.coffre.data.repo

import kotlinx.coroutines.flow.Flow

interface Repository<T> {

    fun observe(id: Long): Flow<T>
    fun observe(): Flow<List<T>>

    suspend fun get(id: Long): T?
    suspend fun get(): List<T>

    suspend fun upsert(item: T)
    suspend fun upsert(items: List<T>)

    suspend fun delete(id: Long): Int
    suspend fun delete(ids: List<Long>): Int
}