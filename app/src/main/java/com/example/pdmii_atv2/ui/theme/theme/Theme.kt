package com.example.pdmii_atv2.ui.theme.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = DouradoSuave,
    onPrimary = TextoEscuro,
    secondary = VerdeOliva,
    onSecondary = TextoEscuro,
    tertiary = RosaGoiaba,
    onTertiary = TextoEscuro,
    background = FundoClaro,
    onBackground = TextoEscuro,
    surface = SuperficieCard,
    onSurface = TextoEscuro
)

@Composable
fun Pdmii_atv2Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography(),
        content = content
    )
}