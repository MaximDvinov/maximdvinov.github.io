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

        screenSizeType.value =
            when {
                windowWidth < 660 -> SizeScreenType.Compact
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