package styles

import component.ColorScheme
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

object Variables {

}

@OptIn(ExperimentalComposeWebApi::class)
object HomeStyleCss : StyleSheet() {
    val verticalLineContainer by style {
        position(Position.Relative)
        height(100.vh)
        top((-51 - 36 - 36).px)
        media(mediaMaxWidth(660.px)){
            self style {
                top((-46 - 16 - 16).px)
            }
        }
    }

    val point by style {
        position(Position.Fixed)
        top(50.percent)
        left(20.percent)
        transform { translate((-50).percent, (-50).percent) }
        width(11.px)
        height(11.px)
        border(6.px, LineStyle.Solid, ColorScheme.secondary)
        borderRadius(50.percent)
        backgroundColor(ColorScheme.primary)

        transitions {
            properties("border") {
                duration = 0.2.s
            }
        }

        self + hover style {
            border(6.px, LineStyle.Solid, ColorScheme.onPrimary)
        }
    }

    val verticalLine by style {
        height(2000.px)
        position(Position.Absolute)
        top(50.vh)
        transform {
            translate((-1.01).px)
        }
        paddingBottom(50.vh)
        left(20.percent)
    }
}