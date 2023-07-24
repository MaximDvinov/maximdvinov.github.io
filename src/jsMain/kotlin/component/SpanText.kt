package component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

object SpanTextStyle : StyleSheet() {
    val bigText by style {
        fontSize(96.px)
        color(Color.white)
        fontFamily("JetBrains Mono-Medium")

        media(mediaMaxWidth(660.px)) {
            self style {
                fontSize(48.px)
            }
        }
    }

    val subTitle by style {
        fontSize(24.px)
        color(ColorScheme.primary)
        fontFamily("JetBrains Mono-Medium")
        media(mediaMaxWidth(660.px)) {
            self style {
                fontSize(20.px)
            }
        }
    }

    val topBar by style {
        fontSize(36.px)
        color(ColorScheme.onPrimary)
        fontFamily("JetBrains Mono-Medium")

        media(mediaMaxWidth(660.px)) {
            self style {
                fontSize(30.px)
                alignSelf(AlignSelf.Center)
            }
        }
    }

    val body by style {
        fontSize(20.px)
        color(ColorScheme.onPrimary)
        fontFamily("JetBrains Mono-Medium")

        media(mediaMaxWidth(660.px)) {
            self style {
                fontSize(18.px)
            }
        }
    }
}

@Composable
fun SpanText(
    text: String,
    style: String = SpanTextStyle.bigText
) {
    P({
        classes(style)
        style {
            margin(0.px)
            padding(0.px)
        }
    }) {
        Text(text)
    }
}