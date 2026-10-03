package com.example.pdmii_atv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.pdmii_atv2.business.FormaPagamento
import com.example.pdmii_atv2.business.ItemPedido
import com.example.pdmii_atv2.ui.theme.screens.CatalogoScreen
import com.example.pdmii_atv2.ui.theme.screens.ItemCardapioView
import com.example.pdmii_atv2.ui.theme.screens.ResumoScreen
import com.example.pdmii_atv2.ui.theme.theme.Pdmii_atv2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Pdmii_atv2Theme {
                AplicacaoPedido()
            }
        }
    }
}

@Composable
fun AplicacaoPedido() {

    var telaAtual by remember {
        mutableStateOf("catalogo")
    }

    var itensCarrinho by remember {
        mutableStateOf<List<ItemPedido>>(emptyList())
    }

    var formaPagamento by remember {
        mutableStateOf(FormaPagamento.DINHEIRO)
    }

    val itensCardapio = listOf(

        ItemCardapioView(
            itemPedido = ItemPedido(
                nome = "Pizza Margherita",
                preco = 42.00,
                quantidade = 1,
                categoria = "Pratos"
            ),
            descricao = "Molho de tomate, manjericão fresco e muçarela"
        ),

        ItemCardapioView(
            itemPedido = ItemPedido(
                nome = "Feijoada completa",
                preco = 58.00,
                quantidade = 1,
                categoria = "Pratos"
            ),
            descricao = "Feijoada tradicional acompanhada de arroz, farofa e couve"
        ),

        ItemCardapioView(
            itemPedido = ItemPedido(
                nome = "Suco de laranja",
                preco = 12.00,
                quantidade = 1,
                categoria = "Bebidas"
            ),
            descricao = "Natural da fruta 500ml"
        )
    )

    if (telaAtual == "catalogo") {

        CatalogoScreen(
            itensCardapio = itensCardapio,
            quantidadeCarrinho = itensCarrinho.size,

            onAdicionarAoCarrinho = { item ->
                itensCarrinho = itensCarrinho + item
            },

            onNavegarParaResumo = {
                telaAtual = "resumo"
            }
        )

    } else {

        ResumoScreen(
            itensSelecionados = itensCarrinho,
            formaPagamentoAtual = formaPagamento,

            onFormaPagamentoAlterada = { forma ->
                formaPagamento = forma
            },

            onVoltarClick = {
                telaAtual = "catalogo"
            },

            onFinalizarPedidoClick = {
                itensCarrinho = emptyList()
                formaPagamento = FormaPagamento.DINHEIRO
                telaAtual = "catalogo"
            }
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewAplicacaoPedido() {
    Pdmii_atv2Theme {
        AplicacaoPedido()
    }
}