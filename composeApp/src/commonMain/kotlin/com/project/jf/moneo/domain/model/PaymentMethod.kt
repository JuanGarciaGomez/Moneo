package com.project.jf.moneo.domain.model

enum class PaymentMethod(val displayName: String) {
    CASH("Efectivo"),
    CREDIT_CARD("Tarjeta de Crédito"),
    DIGITAL_WALLET("Billetera Digital"),
    SAVINGS_ACCOUNT("Cuenta de Ahorros"),
    OTHER("Otro")
}