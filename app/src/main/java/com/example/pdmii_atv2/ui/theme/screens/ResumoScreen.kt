package com.example.pdmii_atv2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pdmii_atv2.business.FormaPagamento
import com.example.pdmii_atv2.business.ItemPedido
import com.example.pdmii_atv2.business.PedidoCalculator
import com.example.pdmii_atv2.ui.components.ItemResumoRow
import com.example.pdmii_atv2.ui.components.LinhaValoresResumo
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumoScreen(
    itensSelecionados: List<ItemPedido>,
    formaPagamentoAtual: FormaPagamento,
    onFormaPagamentoAlterada: (FormaPagamento) -> Unit,
    onVoltarClick: () -> Unit,
    onFinalizarPedidoClick: () -> Unit
) {
    val calculator = remember { PedidoCalculator() }
    val resultado = calculator.calcularPedido(itensSelecionados, formaPagamentoAtual)

    LaunchedEffect(itensSelecionados, formaPagamentoAtual) {
        calculator.gerarRelatorio(itensSelecionados)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Resumo do Pedido") },
                navigationIcon = {
                    IconButton(onClick = onVoltarClick) {
                        Text("←")
                    }
                }
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Button(
                    onClick = onFinalizarPedidoClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Text("[ FINALIZAR PEDIDO ]")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "ITENS",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(itensSelecionados) { item ->
                    val totalItem = item.preco * item.quantidade
                    val precoFmt = String.format(Locale("pt", "BR"), "R$ %.2f", totalItem)
                    ItemResumoRow(
                        nome = item.nome,
                        quantidade = item.quantidade,
                        valorFormatado = precoFmt
                    )
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            Text(
                text = "FORMA DE PAGAMENTO",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            FormaPagamento.entries.forEach { forma ->
                val rotulo = when (forma) {
                    FormaPagamento.DINHEIRO -> "Dinheiro"
                    FormaPagamento.CARTAO -> "Cartão"
                    FormaPagamento.PIX -> "Pix - 10% off"
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .selectable(
                            selected = (forma == formaPagamentoAtual),
                            onClick = { onFormaPagamentoAlterada(forma) },
                            role = Role.RadioButton
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (forma == formaPagamentoAtual),
                        onClick = null,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.secondary
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = rotulo, style = MaterialTheme.typography.bodyMedium)
                }
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))

            LinhaValoresResumo(
                rotulo = "Subtotal",
                valor = String.format(Locale("pt", "BR"), "R$ %.2f", resultado.subtotal)
            )
            LinhaValoresResumo(
                rotulo = "Taxa de serviço",
                valor = String.format(Locale("pt", "BR"), "R$ %.2f", resultado.taxaServico)
            )
            if (resultado.desconto > 0) {
                LinhaValoresResumo(
                    rotulo = "Desconto Pix",
                    valor = String.format(Locale("pt", "BR"), "-R$ %.2f", resultado.desconto)
                )
            }

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            LinhaValoresResumo(
                rotulo = "TOTAL",
                valor = String.format(Locale("pt", "BR"), "R$ %.2f", resultado.total),
                destaque = true
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResumoScreenPreview() {
    val listaExemplo = listOf(
        ItemPedido("Pizza Margherita", 42.00, 1, "Pratos"),
        ItemPedido("Feijoada completa", 58.00, 1, "Pratos"),
        ItemPedido("Suco de laranja", 12.00, 1, "Bebidas")
    )

    ResumoScreen(
        itensSelecionados = listaExemplo,
        formaPagamentoAtual = FormaPagamento.PIX,
        onFormaPagamentoAlterada = {},
        onVoltarClick = {},
        onFinalizarPedidoClick = {}
    )
}