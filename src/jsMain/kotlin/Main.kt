import androidx.compose.runtime.*
import component.ButtonStyle
import component.SpanTextStyle
import component.fadeIn
import component.fadeOut
import kotlinx.browser.window
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.renderComposable
import org.w3c.dom.Element
import org.w3c.dom.HTMLElement
import screen.home.HomeScreen
import screen.project.ProjectScreen
import screen.project.component.SliderStyle
import styles.HomeStyleCss

fun main() {
    renderComposable(rootElementId = "root") {
        Style(ButtonStyle)
        Style(HomeStyleCss)
        Style(SpanTextStyle)
        Style(SliderStyle)

        var hash by remember { mutableStateOf("") }
        var container by remember { mutableStateOf<HTMLElement?>(null) }

        val navigator = NavigationContainer(HomeScreen())

        LaunchedEffect(hash) {
            if (hash == "#sentilens") {
                navigator?.push(ProjectScreen(sentilens))
            } else {
                navigator?.push(HomeScreen())
            }
        }

        window.addEventListener("hashchange", {
            hash = window.location.hash
        })
    }
}

