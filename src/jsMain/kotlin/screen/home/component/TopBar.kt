package screen.home.component

import androidx.compose.runtime.Composable
import component.*
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*

@Composable
fun TopBar() {
    Row(
        styles = {
            padding(0.px)
            justifyContent(JustifyContent.SpaceBetween)
            alignItems(AlignItems.Center)
            property("margin", "36px")
            property("z-index", "100")
        }
    ) {
        SpanText(text = "MaximDvinov", style = SpanTextStyle.topBar)

        StyledButton(text = "github") {
            window.open("https://github.com/MaximDvinov")
        }
    }
}
