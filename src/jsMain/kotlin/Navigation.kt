import androidx.compose.runtime.*
import component.fadeIn
import component.fadeOut
import kotlinx.browser.window
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.dom.Div

abstract class Screen {
    abstract val name: String
    var scrollState = 0.0

    @Composable
    fun contentScreen() {
        Div({
            classes(this@Screen::class.simpleName.toString())
        }) {
            content()
        }
    }

    @Composable
    abstract fun content()
}


val ComposeNavigator = compositionLocalOf<Navigation> {
    error("No screen")
}

@Composable
fun NavigationContainer(initialScreen: Screen?): Navigation? {
    var container: Navigation? = null

    CompositionLocalProvider(ComposeNavigator provides Navigation(initialScreen)) {
        val navigator = ComposeNavigator.current
        container = navigator

        window.document.title = navigator.current().name
        window.document.body?.style?.opacity = "0"
        window.document.body?.fadeIn()

        navigator.current().contentScreen()

        LaunchedEffect(navigator.current()) {
            window.scrollTo(0.0, navigator.current().scrollState)
        }
    }


    return container
}

class Navigation private constructor() {
    private lateinit var backStack: MutableState<List<Screen>>

    constructor(initialScreen: Screen?) : this() {
        backStack = if (initialScreen != null)
            mutableStateOf(listOf(initialScreen))
        else
            mutableStateOf(listOf())
    }

    fun push(screen: Screen) {
        backStack.value = backStack.value.map {
            it.apply {
                if (it == current()) {
                    it.scrollState = window.scrollY
                }
            }
        }

        window.document.body?.style?.opacity = "0"
        window.document.body?.fadeOut {
            backStack.value += screen
        }

    }

    fun pop() {
        window.document.body?.style?.opacity = "0"
        window.document.body?.fadeOut {
            backStack.value -= backStack.value.last()
        }

    }

    fun current(): Screen {
        return backStack.value.last()
    }

}