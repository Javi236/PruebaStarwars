package com.example.start_wars.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CampoTexto(
    label: String,
    valor: String,
    onChange: (String) -> Unit,
    enabled: Boolean = true
) {
    // Hemos eliminado el Text() extra de arriba para evitar la redundancia
    // y para que el Scanner de Accesibilidad no detecte elementos sin etiquetar.
    OutlinedTextField(
        value = valor,
        onValueChange = onChange,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        label = { Text(text = label) }, // El label interno ya cumple la función de accesibilidad
        singleLine = true
    )
}