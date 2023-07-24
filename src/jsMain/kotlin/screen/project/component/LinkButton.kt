package screen.project.component

import Project
import androidx.compose.runtime.Composable
import component.IconButton
import component.Row
import component.SizeScreenType
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Img

@Composable
fun LinkButton(project: Project?, sizeScreenType: SizeScreenType) {
    Row({
        gap(16.px)
        if (sizeScreenType == SizeScreenType.Compact) {
            width(100.percent)
        }

        justifyContent(JustifyContent.SpaceAround)
    }) {
        project?.urls?.forEach {

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