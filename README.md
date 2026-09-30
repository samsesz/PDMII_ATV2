# PDMII_ATV2

# Catálogo Interativo de Restaurante

Aplicativo desenvolvido para a atividade de Desenvolvimento Mobile.

O sistema permite a visualização de um cardápio subdividido em pratos e bebidas, a adição de itens a um carrinho, o cálculo de taxas e descontos de acordo com a forma de pagamento e a consolidação de um recibo digital.

## Colaboradores

* Ananda
* Catarine
* Samia

## Divisão de Responsabilidades

| Membro | Escopo de Responsabilidade |
| ------ | -------------------------- |
| Ananda | Modelagem de Dados         |
| Samia  | Business Logic             |
| Catarine | UI Catálogo                |
| Catarine | UI Resumo                  |


## Log de Saída

### Resultado do cenário de validação

```text
Subtotal: R$ 112,00
Taxa de serviço: R$ 11,20
Desconto Pix: R$ 11,20
Total final: R$ 112,00
```

### Relatório por categoria

```text
Categoria: Pratos
Pizza Margherita - Quantidade: 1
Feijoada completa - Quantidade: 1

Categoria: Bebidas
Suco de laranja - Quantidade: 1
```

## Cenário de Validação

### Itens selecionados

| Item              |    Preço | Quantidade |
| ----------------- | -------: | ---------: |
| Pizza Margherita  | R$ 42,00 |          1 |
| Feijoada completa | R$ 58,00 |          1 |
| Suco de laranja   | R$ 12,00 |          1 |

### Forma de Pagamento
A modelagem das formas de pagamento foi estruturada utilizando uma classe restrita (`sealed class FormaPagamento`), contemplando três opções principais:
- **Dinheiro**: Pagamento em espécie padrão.
- **Cartão**: Pagamento na modalidade cartão de crédito/débito.
- **Pix**: Opção digital que carrega consigo a regra de negócio do percentual de desconto aplicado sobre o pedido (configurado por padrão com **10% de desconto**).

### Resultados esperados

```text
Subtotal: R$ 112,00
Taxa de serviço: R$ 11,20
Desconto Pix: R$ 11,20
Valor Total Final: R$ 112,00
```

## Telas do Sistema

### Tela de Catálogo

<!-- -->

### Tela de Resumo

<!-- -->
