package screen.project

import ComposeNavigator
import Project
import Screen
import androidx.compose.runtime.Composable
import component.Column
import component.Row
import component.SpanText
import component.SpanTextStyle
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.w3c.dom.HTMLElement
import screen.project.component.SliderGallery
import screen.project.component.TabRowProject

class ProjectScreen(private val project: Project) : Screen() {
    override val name = project.title

    @Composable
    override fun content() {
        ProjectScreenContent(project)
    }
}

@Composable
fun ProjectScreenContent(project: Project, ref: (HTMLElement) -> Unit = {}) {
    val navigation = ComposeNavigator.current
    window.document.title = project.title

    Column({
        padding(36.px)
        position(Position.Absolute)
    }, {
        classes("screen")
        ref {
            ref(it)
            onDispose {

            }
        }
    }) {
        TabRowProject(navigation, project)

        Row({
            paddingTop(36.px)
        }) {
            Column({
                flex(1)
            }) {
                SpanText(project.description, style = SpanTextStyle.body)
            }

            Div({
                style { width(16.px) }
            })

            Column({
                flex(1)
            }) {

                SliderGallery({
                    style {
                        width(100.percent)
                    }
                }, list = project.image)
            }
        }
    }

}