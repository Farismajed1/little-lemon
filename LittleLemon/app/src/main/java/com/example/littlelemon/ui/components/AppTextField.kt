package com.example.littlelemon.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue

@Composable
fun AppTextField(label: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, readOnly: Boolean = false) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        textStyle = MaterialTheme.typography.bodySmall,
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
        readOnly = readOnly
    )
}