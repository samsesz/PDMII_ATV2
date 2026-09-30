package com.example.pdmii_atv2.model

sealed class FormaPagamento {
    data object Dinheiro : FormaPagamento()

    data object Cartao : FormaPagamento()

    data class Pix(
        val descontoPercentual: Double = 10.0
    ) : FormaPagamento()
}