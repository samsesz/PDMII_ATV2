package com.example.pdmii_atv2.model

sealed class ItemMenu(
    open val nome: String,
    open val preco: Double,
    open val descricao: String? = null
) {
    data class Prato(
        override val nome: String,
        override val preco: Double,
        override val descricao: String? = null,
        val vegetariano: Boolean
    ) : ItemMenu(nome, preco, descricao)

    data class Bebida(
        override val nome: String,
        override val preco: Double,
        override val descricao: String? = null,
        val alcoolica: Boolean
    ) : ItemMenu(nome, preco, descricao)
}