package screen.project

import ComposeNavigator
import Project
import Screen
import androidx.compose.runtime.*
import component.*
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.web.css.*
import org.w3c.dom.HTMLElement
import screen.project.component.LinkButton
import screen.project.component.SliderGallery
import screen.project.component.TabRowProject

@Serializable
@SerialName("project")
class ProjectScreen(private val project: Project) : Screen {
    override val route = project.title
    override var scrollState: Double = 0.0

    @Composable
    override fun content() {
        ProjectScreenContent(project)
    }
}

@Composable
fun ProjectScreenContent(project: Project, ref: (HTMLElement) -> Unit = {}) {
    val navigation = ComposeNavigator.current
    window.document.title = project.title

    val sizeScreen by rememberScreenSize()
    val padding by remember(sizeScreen) {
        mutableStateOf(if (sizeScreen == SizeScreenType.Compact) 16.px else 36.px)
    }

    LaunchedEffect(project.icon) {
        if (project.icon != null)
            document.changeFavicon(project.icon)
        else
            document.changeFavicon("/images/logo.png")
    }

    Column({
        padding(padding)
    }, {
        ref {
            ref(it)
            onDispose {

            }
        }
    }) {
        TabRowProject(navigation, project, sizeScreen)

        AdaptiveLayout({
            style {
                paddingTop(padding)
                gap(16.px)
            }
        }) {
            Column({
                flex(1)
            }) {
                SpanText(project.description, style = SpanTextStyle.body)
            }
            Column({
                flex(1)
            }) {

                SliderGallery({
                    style {
                        width(100.percent)
                    }
                }, list = project.image)
            }

            if (sizeScreen == SizeScreenType.Compact) {
                LinkButton(project, sizeScreen)
            }
        }
    }
}