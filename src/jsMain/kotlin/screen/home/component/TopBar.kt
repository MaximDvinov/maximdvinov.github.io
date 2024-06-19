package screen.home.component

import androidx.compose.runtime.Composable
import component.*
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img

@Composable
fun TopBar(sizeScreen: SizeScreenType) {
    Row(
        styles = {
            padding(0.px)
            alignItems(AlignItems.Center)
            margin(if (sizeScreen == SizeScreenType.Compact) 16.px else 36.px)
            property("z-index", "100")
        }
    ) {
        Div(
            attrs = {
                classes(ButtonStyle.transparent)
                style {
                    paddingLeft(16.px)
                    paddingRight(16.px)
                }

                onClick {
                    window.open("https://t.me/MaximDvinov")
                }
            }
        ) {
            SpanText(text = "<MD>", style = SpanTextStyle.topBar)
        }


        Div(attrs = {
            style {
                flexGrow(1)
            }
        })

        StyledButton(text = "github") {
            window.open("https://github.com/MaximDvinov")
        }

        Div(attrs = {
            style { width(8.px) }
        })


        IconButton(content = {
            Img(
                src = "/images/tg.svg",
                attrs = {
                    style { width(36.px);height(36.px) }
                }
            )
        }) {
            window.open("https://t.me/MaximDvinov")
        }


//        StyledButton(text = "kwork") {
//            window.open("https://kwork.ru/user/maximdvinov")
//        }
    }
}
