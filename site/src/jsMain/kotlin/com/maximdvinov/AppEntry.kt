package com.maximdvinov

import androidx.compose.runtime.Composable
import com.maximdvinov.ui.PortfolioColors
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fontFamily
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import org.jetbrains.compose.web.css.vh

@App
@Composable
fun AppEntry(content: @Composable () -> Unit) {
    SilkApp {
        Surface(
            Modifier
                .minHeight(100.vh)
                .backgroundColor(PortfolioColors.Paper)
                .color(PortfolioColors.Ink)
                .fontFamily("Inter", "system-ui", "-apple-system", "BlinkMacSystemFont", "Segoe UI", "sans-serif")
        ) {
            content()
        }
    }
}
