package com.example.start_wars.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//Campo que usamos muchas veces por lo que lo hacemos composable
@Composable
fun CampoTexto(
    label: String,
    valor: String,
    onChange: (String) -> Unit,
    enabled: Boolean = true
) {
    Text(text = label, style = MaterialTheme.typography.bodySmall)
    OutlinedTextField(
        value = valor,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        label = { Text(label) }
    )
}

//Aqui saale doble titulo porque aqui tengo que tambien muestra antes de campo un mensaje de lo que sea va a indicar
@Preview(showBackground = true)
@Composable
fun CampoTextoPreview() {
    CampoTexto(
        label = "Título",
        valor = "A New Hope",
        onChange = {}
    )
}

