package screen.home

import ComposeNavigator
import Project
import Screen
import androidx.compose.runtime.*
import component.*
import cosmos
import kotlinx.browser.window
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import screen.home.component.TopBar
import screen.project.ProjectScreen
import sentilens
import styles.HomeStyleCss
import kotlin.math.PI
import kotlin.math.cos

const val period = 500.0

class HomeScreen : Screen() {
    override val name = "MaximDvinov"

    @Composable
    override fun content() {
        HomeScreenContent()
    }
}

@OptIn(ExperimentalComposeWebApi::class)
@Composable
fun HomeScreenContent(ref: (HTMLElement) -> Unit = {}) {
    val navigationState = ComposeNavigator.current

    var scrollState by remember {
        mutableStateOf(window.scrollY)
    }

    var project by remember {
        mutableStateOf<Project?>(null)
    }

    window.document.title = "MaximDvinov"

    LaunchedEffect(scrollState) {
        project = when (scrollState) {
            in 0.0..period -> null
            in period..3 * period -> sentilens
            else -> cosmos
        }
    }

    window.addEventListener("scroll", {
        scrollState = window.scrollY
    })

    Column(attr = {
        classes("screen")
        ref {
            ref(it)
            onDispose {

            }
        }
    }) {
        TopBar()

        Div({
            classes(HomeStyleCss.verticalLineContainer)
        }) {
            Div({ classes(HomeStyleCss.verticalLine) }) {
                Div({
                    style {
                        height(100.percent)
                        width(2.px)
                        marginRight(1.px)
                        backgroundColor(ColorScheme.primary)
                    }
                }) {

                }
            }

            Div({
                classes(HomeStyleCss.point)
                style {
                    width((11 * (getOpacity(scrollState) + 0.5)).px)
                    height((11 * (getOpacity(scrollState) + 0.5)).px)
                }
                onClick {
                    when (scrollState) {
                        in 0.0..period -> animateScrollTo(2 * period, period)
                        in period..3 * period -> animateScrollTo(4 * period, period)
                        else -> animateScrollTo(0.0, 3 * period)
                    }
                }
            }) { }

            var visibleImage by remember { mutableStateOf(false) }

            Column(styles = {
                position(Position.Fixed)
                top(50.percent)
                left(25.percent)
                transform { translate((0).percent, (-50).percent) }
                justifyContent(JustifyContent.Center)
                alignContent(AlignContent.Start)
                height(100.vh)
            }) {
                Column(attr = {
                    onMouseOver {
                        visibleImage = true
                    }
                    onMouseOut {
                        visibleImage = false
                    }
                    onClick {
                        if (project != null) navigationState.push(ProjectScreen(project!!))
                    }
                    style {
                        opacity(getOpacity(scrollState))
                    }
                }) {
                    SpanText(
                        text = project?.title ?: "android developer", style = SpanTextStyle.bigText
                    )

                    var imageRef by remember {
                        mutableStateOf<HTMLImageElement?>(null)
                    }

                    LaunchedEffect(visibleImage, project) {
                        if (visibleImage && project != null) {
                            imageRef?.imageVisibleIn(200.0)
                        } else {
                            imageRef?.imagVisibleOut(200.0)

                        }
                    }

                    Img(src = project?.image?.first() ?: "", attrs = {
                        style {
                            width(50.vw)
                            height(0.px)
                            borderRadius(24.px)
                            property("object-fit", "cover")
                        }

                        ref {
                            imageRef = it
                            this.onDispose {
                                imageRef = null
                            }
                        }
                    })

                    SpanText(
                        project?.subtitle ?: "", style = SpanTextStyle.subTitle
                    )
                }

            }
        }

    }
}

fun getOpacity(scrollState: Double) = (cos((PI * scrollState) / period) + 1) / 2
