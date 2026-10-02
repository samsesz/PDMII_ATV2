package com.example.pdmii_atv2.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pdmii_atv2.business.ItemPedido
import com.example.pdmii_atv2.ui.components.ItemCatalogoCard

data class ItemCardapioView(
    val itemPedido: ItemPedido,
    val descricao: String? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(
    itensCardapio: List<ItemCardapioView>,
    quantidadeCarrinho: Int,
    onAdicionarAoCarrinho: (ItemPedido) -> Unit,
    onNavegarParaResumo: () -> Unit
) {
    val pratos = itensCardapio.filter { it.itemPedido.categoria.equals("Pratos", ignoreCase = true) }
    val bebidas = itensCardapio.filter { it.itemPedido.categoria.equals("Bebidas", ignoreCase = true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cardápio") },
                actions = {
                    Text(
                        text = "🛒 ($quantidadeCarrinho)",
                        modifier = Modifier.padding(end = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Button(
                    onClick = onNavegarParaResumo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text("[ VER RESUMO ]")
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "PRATOS",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(pratos) { pratoView ->
                ItemCatalogoCard(
                    nome = pratoView.itemPedido.nome,
                    preco = pratoView.itemPedido.preco,
                    descricao = pratoView.descricao,
                    onAdicionarClick = { onAdicionarAoCarrinho(pratoView.itemPedido) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "BEBIDAS",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(bebidas) { bebidaView ->
                ItemCatalogoCard(
                    nome = bebidaView.itemPedido.nome,
                    preco = bebidaView.itemPedido.preco,
                    descricao = bebidaView.descricao,
                    onAdicionarClick = { onAdicionarAoCarrinho(bebidaView.itemPedido) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CatalogoScreenPreview() {
    val listaExemplo = listOf(
        ItemCardapioView(
            itemPedido = ItemPedido("Pizza Margherita", 42.00, 1, "Pratos"),
            descricao = "Molho de tomate, manjericão fresco e muçarela"
        ),
        ItemCardapioView(
            itemPedido = ItemPedido("Feijoada completa", 58.00, 1, "Pratos"),
            descricao = null
        ),
        ItemCardapioView(
            itemPedido = ItemPedido("Suco de laranja", 12.00, 1, "Bebidas"),
            descricao = "Natural da fruta 500ml"
        )
    )

    CatalogoScreen(
        itensCardapio = listaExemplo,
        quantidadeCarrinho = 3,
        onAdicionarAoCarrinho = {},
        onNavegarParaResumo = {}
    )
}