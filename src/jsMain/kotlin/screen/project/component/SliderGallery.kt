package screen.project.component

import androidx.compose.runtime.*
import component.ColorScheme
import component.Column
import component.Row
import kotlinx.browser.window
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.attributes.Draggable
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.DragEvent
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.events.Event

object SliderStyle : StyleSheet() {

    val sliderContainer by style {
        width(50.vw)
        overflow("hidden")
    }

    @OptIn(ExperimentalComposeWebApi::class)
    val slider by style {
        display(DisplayStyle.Flex)
        flex(1)
        transitions {
            properties("transform") {
                duration = 0.3.s
                this.timingFunction = AnimationTimingFunction.Ease
            }
        }
        cursor("grab")
    }

    val sliderImg by style {
        width(100.percent)
        property("height", "auto")
    }

    val indicator by style {
        width(10.px)
        height(10.px)
        borderRadius(50.percent)
        backgroundColor(Color("#D9D9D990"))
        marginRight(0.px)
        display(DisplayStyle.InlineBlock)
    }


}

@Composable
fun SliderGallery(attrsScope: (AttrsScope<HTMLDivElement>.() -> Unit), list: List<String>) {
    var slider by remember { mutableStateOf<HTMLDivElement?>(null) }
    var sliderImages by remember { mutableStateOf(listOf<HTMLImageElement>()) }
    var slideWidth by remember { mutableStateOf(0) }

    LaunchedEffect(sliderImages) {
        if (sliderImages.isNotEmpty()) {
            slideWidth = sliderImages[0].clientWidth
        }
    }

    var currentIndex by remember { mutableStateOf(0) }
    var startX by remember { mutableStateOf(0) }
    var isDragging by remember { mutableStateOf(false) }

    fun updateSlider() {
        slider?.style?.transform = "translateX(${-slideWidth * currentIndex}px)"
        console.log(slider?.style?.transform)
    }

    Column(attr = {
        classes(SliderStyle.sliderContainer)
        attrsScope()
    }) {
        Div({
            style {
                width(100.percent)
                overflow("hidden")
                borderRadius(24.px)
            }
        }){
            Div({
                draggable(Draggable.True)
                classes(SliderStyle.slider)
                ref {
                    slider = it

                    onDispose {
                        slider = null
                    }
                }
            }) {
                list.forEach {
                    Img(src = it, attrs = {
                        classes(SliderStyle.sliderImg)
                        addEventListener("dragstart") { drag -> drag.preventDefault() }
                        ref {
                            sliderImages += it
                            onDispose {
                                sliderImages -= it
                            }
                        }
                    })
                }
            }

        }

        Row({
            justifyContent(JustifyContent.Center)
            alignContent(AlignContent.Start)
            gap(8.px)
            paddingTop(16.px)
            paddingBottom(16.px)
        }) {
            list.forEachIndexed { index, it ->
                Div({
                    classes(SliderStyle.indicator)
                    onClick {
                        currentIndex = index
                        updateSlider()
                    }
                    style {
                        if (index == currentIndex) {
                            backgroundColor(ColorScheme.primary)
                        }
                    }
                })
            }
        }
    }



    LaunchedEffect(Unit) {

        fun handleDragStart(e: Event) {
            isDragging = true
            startX = e.asDynamic().clientX as? Int ?: e.asDynamic().touches[0].clientX as Int
            console.log("startX: $startX")
        }

        fun handleDragEnd(e: Event) {
            if (isDragging) {
                val endX = e.asDynamic().clientX as? Int ?: e.asDynamic().changedTouches[0].clientX as Int
                val deltaX = endX - startX

                if (deltaX > 0) {
                    currentIndex--

                    if (currentIndex < 0) {
                        currentIndex = 0
                    }
                } else {
                    if (deltaX < 0) {
                        currentIndex++

                        if (currentIndex >= sliderImages.size) {
                            currentIndex = sliderImages.lastIndex
                        }
                    }
                }

                updateSlider()
                slider?.classList?.remove("dragging")
            }
            console.log("endX: $isDragging")
        }

//        fun nextSlide() {
//            currentIndex++
//            if (currentIndex >= sliderImages.size) {
//                currentIndex = 0
//            }
//            updateSlider()
//            console.log("currentIndex: $currentIndex")
//        }

        slider?.addEventListener("mousedown", ::handleDragStart)
        slider?.addEventListener("touchstart", ::handleDragStart, js("{ passive: true }"))

        slider?.addEventListener("mouseup", ::handleDragEnd)
        slider?.addEventListener("touchend", ::handleDragEnd, js("{ passive: true }"))

//        window.setInterval(::nextSlide, 3000)
    }
}