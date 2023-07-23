package component

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*

@OptIn(ExperimentalComposeWebApi::class)
fun CSSBuilder.transition(property: String, duration: CSSSizeValue<out CSSUnitTime>? = 0.1.s) {
    transitions {
        this.properties(property) {
            this.duration = duration
        }
    }
}