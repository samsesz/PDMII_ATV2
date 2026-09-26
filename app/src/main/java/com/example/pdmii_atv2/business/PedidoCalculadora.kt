package com.example.pdmii_atv2.business

import android.util.Log

class PedidoCalculator {

    private val taxaServico = 0.10
    private val descontoPix = 0.10

    fun calcularSubtotal(itens: List<ItemPedido>): Double {

        var subtotal = 0.0

        for (item in itens) {
            subtotal += item.preco * item.quantidade
        }

        return subtotal
    }

    fun calcularTaxaServico(subtotal: Double): Double {

        return subtotal * taxaServico
    }

    fun calcularDesconto(
        subtotal: Double,
        formaPagamento: FormaPagamento
    ): Double {

        if (formaPagamento == FormaPagamento.PIX) {
            return subtotal * descontoPix
        }

        return 0.0
    }

    fun calcularTotal(
        subtotal: Double,
        taxaServico: Double,
        desconto: Double
    ): Double {

        return subtotal + taxaServico - desconto
    }

    fun calcularPedido(
        itens: List<ItemPedido>,
        formaPagamento: FormaPagamento
    ): ResultadoPedido {

        val subtotal = calcularSubtotal(itens)

        val taxa = calcularTaxaServico(subtotal)

        val desconto = calcularDesconto(
            subtotal,
            formaPagamento
        )

        val total = calcularTotal(
            subtotal,
            taxa,
            desconto
        )

        return ResultadoPedido(
            subtotal,
            taxa,
            desconto,
            total
        )
    }

    fun gerarRelatorio(itens: List<ItemPedido>) {

        val grupos = itens.groupBy {
            it.categoria
        }

        for ((categoria, lista) in grupos) {

            Log.d("PEDIDO", "Categoria: $categoria")

            for (item in lista) {

                Log.d(
                    "PEDIDO",
                    "${item.nome} - Quantidade: ${item.quantidade}"
                )
            }
        }
    }
}