package screen.project.component

import Navigation
import Project
import androidx.compose.runtime.Composable
import component.IconButton
import component.Row
import component.SpanText
import component.SpanTextStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.css.flex
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun TabRowProject(navigation: Navigation, project: Project?) {
    Row {
        IconButton(content = {
            Img(
                src = "images/arrowleft.svg", attrs = {
                    style {
                        width(48.px)
                        height(48.px)
                    }
                }
            )
        }) {
            if (window.location.hash.isNotBlank()) {
                window.location.hash = ""
            } else {
                navigation.pop()
            }
        }

        Div({
            style {
                width(16.px)
            }
        })

        SpanText(
            text = project?.title ?: "", style = SpanTextStyle.topBar
        )

        Div({
            style {
                width(16.px)
                flex(1)
            }
        })

        project?.urls?.forEach {
            Div({
                style {
                    width(16.px)
                }
            })

            IconButton(content = {
                Img(
                    src = it.icon, attrs = {
                        style {
                            width(48.px)
                            height(48.px)
                        }
                    }
                )
            }) {
                window.open(it.url)
            }

        }
    }
}