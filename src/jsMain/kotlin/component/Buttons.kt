package component

import androidx.compose.runtime.Composable
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Text

object ButtonStyle : StyleSheet() {
    @OptIn(ExperimentalComposeWebApi::class)
    val transparent by style {
        fontSize(24.px)
        color(Color.white)
        fontWeight(300)
        fontFamily("JetBrains Mono-Medium")
        backgroundColor(Color.transparent)
        border(0.px)
        borderRadius(10.px)
        padding(10.px)

        transitions {
            this.properties("background-color") {
                duration = 0.1.s
            }
        }

        self + hover style {
            backgroundColor(ColorScheme.primary)
        }

        media(mediaMaxWidth(660.px)){
            self style {
                fontSize(20.px)
            }
        }
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val iconButton by style {
        color(Color.white)
        fontWeight(300)
        backgroundColor(ColorScheme.secondary)
        padding(0.px)
        border(0.px)
        borderRadius(16.px)
        width(48.px)
        height(48.px)

        transitions {
            this.properties("background-color") {
                duration = 0.1.s
            }
        }

        self + hover style {
            backgroundColor(ColorScheme.primary)
        }
    }
}

@Composable
fun StyledButton(style: String = ButtonStyle.transparent, text: String, onClick: () -> Unit) {
    Button({
        classes(style)

        onClick {
            onClick()
        }
    }) {
        Text(text)
    }
}

@Composable
fun IconButton(style: String = ButtonStyle.iconButton, content: @Composable () -> Unit, onClick: () -> Unit) {
    Button({
        classes(style)
        onClick {
            onClick()
        }
    }) {
        content()
    }
}
