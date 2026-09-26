package com.example.pdmii_atv2.business

data class ResultadoPedido(
    val subtotal: Double,
    val taxaServico: Double,
    val desconto: Double,
    val total: Double
)