import androidx.compose.runtime.*
import component.*
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import screen.home.HomeScreen
import screen.project.ProjectScreen

enum class LocalStorageKeys {
    SCROLL_POSITION, SCREENS;

    override fun toString(): String {
        return name
    }
}

val json = Json {
    serializersModule = SerializersModule {
        polymorphic(Screen::class) {
            subclass(HomeScreen::class)
            subclass(ProjectScreen::class)
        }
    }
}

interface Screen {
    val route: String
    var scrollState: Double

    @Composable
    fun content()
}


val ComposeNavigator = compositionLocalOf<Navigation> {
    error("No screen")
}

@Composable
fun NavigationContainer(initialScreen: Screen): Navigation? {
    var container: Navigation? = null

    CompositionLocalProvider(ComposeNavigator provides Navigation(initialScreen)) {
        val navigator = ComposeNavigator.current
        container = navigator

        LaunchedEffect(Unit) {
            window.addEventListener("beforeunload", {
                val tmp = json.encodeToString(navigator.backStack.value)
                localStorage.setDataWithExpiration(LocalStorageKeys.SCREENS.name, tmp, 1)
            })
        }

        val currentScreen = navigator.current()

        window.document.title = currentScreen?.route ?: ""
        window.document.body?.style?.opacity = "0"
        window.document.body?.fadeIn()

        currentScreen?.content()

        LaunchedEffect(currentScreen) {
            window.scrollTo(0.0, currentScreen?.scrollState ?: 0.0)
        }
    }

    return container
}

class Navigation private constructor() {
    lateinit var backStack: MutableState<List<Screen>>

    constructor(initialScreen: Screen) : this() {
        val list: List<Screen>? =
            localStorage.getDataWithExpiration(LocalStorageKeys.SCREENS.name)?.let { screens ->
                json.decodeFromString(screens)
            }

        backStack = mutableStateOf(list ?: listOf(initialScreen))
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
        backStack.value = backStack.value.map {
            it.apply {
                if (it == current()) {
                    it.scrollState = window.scrollY
                }
            }
        }

        window.document.body?.style?.opacity = "0"
        window.document.body?.fadeOut {
            backStack.value -= backStack.value.last()
        }
    }

    fun current(): Screen? {
        return backStack.value.lastOrNull()
    }
}