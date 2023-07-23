package component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text

object SpanTextStyle : StyleSheet() {
    val bigText by style {
        fontSize(96.px)
        color(Color.white)
        fontFamily("JetBrains Mono-Medium")
    }

    val subTitle by style {
        fontSize(24.px)
        color(ColorScheme.primary)
        fontFamily("JetBrains Mono-Medium")
    }

    val topBar by style {
        fontSize(36.px)
        color(ColorScheme.onPrimary)
        fontFamily("JetBrains Mono-Medium")
    }

    val body by style {
        fontSize(20.px)
        color(ColorScheme.onPrimary)
        fontFamily("JetBrains Mono-Medium")
    }
}

@Composable
fun SpanText(
    text: String,
    style: String = SpanTextStyle.bigText
) {
    Div({
        classes(style)
    }) {
        Text(text)
    }
}