package screen.project.component

import Navigation
import Project
import androidx.compose.runtime.Composable
import component.*
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun TabRowProject(navigation: Navigation, project: Project?, sizeScreen: SizeScreenType) {
    Row(
        styles = {
            alignContent(AlignContent.Baseline)
        }
    ) {
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

        if (sizeScreen != SizeScreenType.Compact) {
            LinkButton(project, sizeScreen)
        }

    }
}

