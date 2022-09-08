package com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util

sealed class NoteSortingType(val orderIn: OrderIn) {
    class Title(orderIn: OrderIn):NoteSortingType(orderIn)
    class Date(orderIn: OrderIn):NoteSortingType(orderIn)
    class Color(orderIn: OrderIn):NoteSortingType(orderIn)

    fun copy(orderIn: OrderIn):NoteSortingType{
        return when(this){
            is Title -> Title(orderIn)
            is Date -> Date(orderIn)
            is Color -> Color(orderIn)
        }
    }
}