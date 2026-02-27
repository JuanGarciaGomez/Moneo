package com.project.jf.moneo.domain.model

enum class TransactionCategory(val displayName: String) {
    FOOD("Alimentación"),
    TRANSPORTATION("Transporte"),
    HOUSING("Vivienda/Alquiler"),
    SERVICES("Servicios Públicos"),
    ENTERTAINMENT("Entretenimiento"),
    HEALTH("Salud"),
    SHOPPING("Compras"),
    EDUCATION("Educación"),
    OTHERS("Otros"),
}