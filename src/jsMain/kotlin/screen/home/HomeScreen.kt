package screen.home

import ComposeNavigator
import Project
import Screen
import androidx.compose.runtime.*
import component.*
import cosmos
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Img
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLImageElement
import runTracker
import screen.home.component.TopBar
import screen.project.ProjectScreen
import sentilens
import styles.HomeStyleCss
import kotlin.math.PI
import kotlin.math.cos

const val period = 500.0

@Serializable
@SerialName("home")
class HomeScreen : Screen {
    override val route = "MaximDvinov"
    override var scrollState: Double = 0.0

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

    val sizeScreen by rememberScreenSize()

    var hash by remember {
        console.log(window.location.hash)
        mutableStateOf(window.location.hash)
    }

    window.document.title = "MaximDvinov"

    LaunchedEffect(Unit) {
        document.changeFavicon("/images/logo.svg")
    }

    LaunchedEffect(scrollState) {
        project = when {
            scrollState <= period -> null
            scrollState in period..3 * period -> sentilens
            scrollState in 3 * period..5 * period -> runTracker
            else -> cosmos
        }
    }

    LaunchedEffect(Unit) {
        window.addEventListener("scroll", {
            scrollState = window.scrollY
        })

        window.addEventListener("hashchange", {
            console.log(window.location.hash)
            hash = window.location.hash
        })
    }


    Column(attr = {
        ref {
            ref(it)
            onDispose {

            }
        }
    }) {
        TopBar(sizeScreen)

        Div({
            classes(HomeStyleCss.verticalLineContainer)
            style {
                if (sizeScreen == SizeScreenType.Compact) {
                    left((-13).percent)
                }
            }
        }) {
            Div({ classes(HomeStyleCss.verticalLine) }) {
                Div({
                    style {
                        height(100.percent)
                        width(2.px)
                        marginRight(1.px)
                        backgroundColor(ColorScheme.primary)
                    }
                })
            }

            Div({
                classes(HomeStyleCss.point)
                style {
                    val size = if (sizeScreen == SizeScreenType.Compact) 16 else 11
                    width((size * (getOpacity(scrollState) + 0.5)).px)
                    height((size * (getOpacity(scrollState) + 0.5)).px)

                    if (sizeScreen == SizeScreenType.Compact) {
                        left((7).percent)
                    }

                }
                onClick {
                    when (scrollState) {
                        in 0.0..period -> animateScrollTo(2 * period, period)
                        in period..3 * period -> animateScrollTo(4 * period, period)
                        in 3 * period..5 * period -> animateScrollTo(6 * period, period)
                        else -> animateScrollTo(0.0, 3 * period)
                    }
                }
            })

            var visibleImage by remember(sizeScreen) { mutableStateOf(sizeScreen == SizeScreenType.Compact) }

            Column(styles = {
                position(Position.Fixed)
                top(50.percent)
                left(if (sizeScreen == SizeScreenType.Compact) 15.percent else 25.percent)
                transform { translate((0).percent, (-50).percent) }
                justifyContent(JustifyContent.Center)
                alignContent(AlignContent.Start)
                height(200.vh)
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

                    if (hash == "#me" && project == null) {
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
                            P({
                                classes(SpanTextStyle.topBar)
                                style {
                                    color(ColorScheme.primary)
                                    margin(0.px)
                                    padding(0.px)
                                }
                            }) {
                                Text("Свяжитесь со мной в tg: @MaximDvinov")
                            }
                        }
                    }


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

                    if (!project?.image?.first().isNullOrBlank()) {
                        Img(src = project?.image?.first() ?: "", attrs = {
                            style {
                                width(if (sizeScreen == SizeScreenType.Compact) 80.vw else 50.vw)
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
                    }

                    SpanText(
                        project?.subtitle ?: "", style = SpanTextStyle.subTitle
                    )
                }
            }
        }

    }
}

fun getOpacity(scrollState: Double) = (cos((PI * scrollState) / period) + 1) / 2
