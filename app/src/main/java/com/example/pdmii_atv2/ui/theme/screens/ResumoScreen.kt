package com.example.pdmii_atv2.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pdmii_atv2.business.FormaPagamento
import com.example.pdmii_atv2.business.ItemPedido
import com.example.pdmii_atv2.business.PedidoCalculator
import com.example.pdmii_atv2.ui.theme.components.ItemResumoRow
import com.example.pdmii_atv2.ui.theme.components.LinhaValoresResumo
import java.util.Locale
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumoScreen(
    itensSelecionados: List<ItemPedido>,
    formaPagamentoAtual: FormaPagamento,
    onFormaPagamentoAlterada: (FormaPagamento) -> Unit,
    onVoltarClick: () -> Unit,
    onFinalizarPedidoClick: () -> Unit
) {
    val calculator = PedidoCalculator()

    val resultado = calculator.calcularPedido(
        itensSelecionados,
        formaPagamentoAtual
    )

    LaunchedEffect(itensSelecionados) {
        calculator.gerarRelatorio(itensSelecionados)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Resumo do pedido")
                },
                navigationIcon = {
                    TextButton(
                        onClick = onVoltarClick
                    ) {
                        Text("VOLTAR")
                    }
                }
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {

            item {
                Text(
                    text = "Itens do pedido",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(itensSelecionados) { item ->

                val valorItem = item.preco * item.quantidade

                ItemResumoRow(
                    nome = item.nome,
                    quantidade = item.quantidade,
                    valorFormatado = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        valorItem
                    )
                )
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Forma de pagamento",
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                FormaPagamento.values().forEach { forma ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        RadioButton(
                            selected = formaPagamentoAtual == forma,
                            onClick = {
                                onFormaPagamentoAlterada(forma)
                            }
                        )

                        Text(
                            text = when (forma) {
                                FormaPagamento.DINHEIRO -> "Dinheiro"
                                FormaPagamento.CARTAO -> "Cartão"
                                FormaPagamento.PIX -> "Pix"
                            },
                            modifier = Modifier.padding(
                                top = 12.dp,
                                start = 8.dp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                LinhaValoresResumo(
                    rotulo = "Subtotal",
                    valor = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        resultado.subtotal
                    )
                )

                LinhaValoresResumo(
                    rotulo = "Taxa de serviço",
                    valor = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        resultado.taxaServico
                    )
                )

                if (resultado.desconto > 0) {
                    LinhaValoresResumo(
                        rotulo = "Desconto Pix",
                        valor = String.format(
                            Locale("pt", "BR"),
                            "- R$ %.2f",
                            resultado.desconto
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinhaValoresResumo(
                    rotulo = "TOTAL",
                    valor = String.format(
                        Locale("pt", "BR"),
                        "R$ %.2f",
                        resultado.total
                    ),
                    destaque = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onFinalizarPedidoClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("FINALIZAR PEDIDO")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}