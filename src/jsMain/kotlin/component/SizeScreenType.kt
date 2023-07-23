package component

import androidx.compose.runtime.*
import kotlinx.browser.window
import org.w3c.dom.events.Event

enum class SizeScreenType {
    Compact, Medium, Expanded
}

@Composable
fun rememberScreenSize(): MutableState<SizeScreenType> {
    val screenSizeType = remember { mutableStateOf(SizeScreenType.Medium) }

    fun updateDeviceType() {
        val windowWidth = window.screen.width
        val windowHeight = window.screen.height

        screenSizeType.value =
            when {
                windowWidth < 600 -> SizeScreenType.Compact
                windowWidth < 840 -> SizeScreenType.Medium
                else -> SizeScreenType.Expanded
            }
    }

    updateDeviceType()

    val resizeListener: (Event) -> Unit = {
        updateDeviceType()
    }

    window.addEventListener("resize", resizeListener)



    return screenSizeType
}