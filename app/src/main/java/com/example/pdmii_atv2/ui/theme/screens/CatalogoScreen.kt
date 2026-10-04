package com.example.pdmii_atv2.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdmii_atv2.business.ItemPedido
import com.example.pdmii_atv2.ui.theme.components.ItemCatalogoCard

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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cardápio")
                },
                actions = {
                    Text(
                        text = "Carrinho: $quantidadeCarrinho",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
            )
        },
        bottomBar = {
            Button(
                onClick = onNavegarParaResumo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("VER RESUMO")
            }
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            item {
                Text(
                    text = "Pratos",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(
                itensCardapio.filter {
                    it.itemPedido.categoria == "Pratos"
                }
            ) { item ->

                ItemCatalogoCard(
                    nome = item.itemPedido.nome,
                    preco = item.itemPedido.preco,
                    descricao = item.descricao,
                    onAdicionarClick = {
                        onAdicionarAoCarrinho(item.itemPedido)
                    }
                )
            }

            item {
                Text(
                    text = "Bebidas",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(
                        top = 16.dp,
                        bottom = 8.dp
                    )
                )
            }

            items(
                itensCardapio.filter {
                    it.itemPedido.categoria == "Bebidas"
                }
            ) { item ->

                ItemCatalogoCard(
                    nome = item.itemPedido.nome,
                    preco = item.itemPedido.preco,
                    descricao = item.descricao,
                    onAdicionarClick = {
                        onAdicionarAoCarrinho(item.itemPedido)
                    }
                )
            }
        }
    }
}