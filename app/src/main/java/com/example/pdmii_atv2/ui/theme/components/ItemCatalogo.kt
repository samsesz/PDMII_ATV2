package com.example.pdmii_atv2.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ItemResumoRow(
    nome: String,
    quantidade: Int,
    valorFormatado: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$nome (x$quantidade)",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = valorFormatado,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun LinhaValoresResumo(
    rotulo: String,
    valor: String,
    destaque: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = rotulo,
            style = if (destaque) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (destaque) FontWeight.Bold else FontWeight.Normal
        )
        Text(
            text = valor,
            style = if (destaque) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (destaque) FontWeight.Bold else FontWeight.Normal,
            color = if (destaque) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
        )
    }
}