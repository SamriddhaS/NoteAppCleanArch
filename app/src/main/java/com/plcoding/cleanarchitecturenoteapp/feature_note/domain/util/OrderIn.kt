package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

sealed class OrderIn {
    object Ascending:OrderIn()
    object Descending:OrderIn()
}