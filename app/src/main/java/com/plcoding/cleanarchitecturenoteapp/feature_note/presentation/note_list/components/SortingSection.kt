package com.plcoding.cleanarchitecturenoteapp.feature_note.presentation.note_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.NoteSortingType
import com.plcoding.cleanarchitecturenoteapp.feature_note.domain.util.OrderIn

@Composable
fun SortingSection(
    modifier: Modifier = Modifier,
    noteSortingType: NoteSortingType = NoteSortingType.Date(OrderIn.Descending),
    onSortingChange:(NoteSortingType) -> Unit
){
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Text",
                selected = noteSortingType is NoteSortingType.Title,
                onCheck = { onSortingChange(NoteSortingType.Title(noteSortingType.orderIn)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Date",
                selected = noteSortingType is NoteSortingType.Date,
                onCheck = { onSortingChange(NoteSortingType.Date(noteSortingType.orderIn)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Color",
                selected = noteSortingType is NoteSortingType.Color,
                onCheck = { onSortingChange(NoteSortingType.Color(noteSortingType.orderIn)) }
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = "Ascending",
                selected = noteSortingType.orderIn is OrderIn.Ascending,
                onCheck = { onSortingChange(noteSortingType.copy(OrderIn.Ascending)) }
            )

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Descending",
                selected = noteSortingType.orderIn is OrderIn.Descending,
                onCheck = { onSortingChange(noteSortingType.copy(OrderIn.Descending)) }
            )
        }

    }
}