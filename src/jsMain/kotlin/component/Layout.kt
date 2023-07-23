package component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.ElementScope
import org.w3c.dom.HTMLDivElement

@Composable
fun AdaptiveLayout(
    attr: (AttrsScope<HTMLDivElement>.() -> Unit) = {},
    content: @Composable () -> Unit
) {
    val sizeScreen by rememberScreenSize()

    Div({
        style {
            display(DisplayStyle.Flex)
            flexDirection(if (sizeScreen == SizeScreenType.Compact) FlexDirection.Column else FlexDirection.Row)
        }
        attr()
    }) {
        content()
    }
}

@Composable
fun Column(
    styles: StyleScope.() -> Unit = {},
    attr: (AttrsScope<HTMLDivElement>.() -> Unit) = {},
    content: @Composable () -> Unit
) {
    Div({
        attr()
        style {
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Column)
            styles()
        }
    }) {
        content()
    }
}

@Composable
fun Row(
    styles: StyleScope.() -> Unit = {},
    content: @Composable ElementScope<HTMLDivElement>.() -> Unit
) {
    Div({
        style {
            styles()
            display(DisplayStyle.Flex)
            flexDirection(FlexDirection.Row)
        }
    }) {
        content()
    }
}