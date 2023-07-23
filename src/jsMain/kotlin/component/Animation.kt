package component

import kotlinx.browser.window
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.HTMLElement
import kotlin.math.max
import kotlin.math.min

fun HTMLElement.fadeIn(duration: Double = 200.0, onAnimationEnd: () -> Unit = {}) {
    val targetOpacity = 1.0
    var start: Double? = null

    fun step(timestamp: Double) {
        if (start == null) start = timestamp
        val progress = timestamp - (start ?: timestamp)
        val opacity = min(progress / duration, targetOpacity)

        style.opacity = opacity.toString()

        if (progress < duration) {
            window.requestAnimationFrame(::step)
        } else {
            onAnimationEnd()
        }

    }

    window.requestAnimationFrame(::step)
}

fun HTMLElement.fadeOut(duration: Double = 200.0, onAnimationEnd: () -> Unit = {}) {
    val targetOpacity = 0.0
    var start: Double? = null

    fun step(timestamp: Double) {
        if (start == null) start = timestamp
        val progress = timestamp - (start ?: timestamp)
        val opacity = max(1 - progress / duration, targetOpacity)

        style.opacity = opacity.toString()

        if (progress < duration) {
            window.requestAnimationFrame(::step)
        } else {
            onAnimationEnd()
        }

    }

    window.requestAnimationFrame(::step)
}

fun HTMLElement.imageVisibleIn(duration: Double) {
    val targetOpacity = 1.0
    var start: Double? = null

    fun step(timestamp: Double) {
        if (start == null) start = timestamp
        val progress = timestamp - (start ?: timestamp)
        val opacity = min(progress / duration, targetOpacity)

        style.opacity = opacity.toString()
        style.height = "${50.vh.value * opacity}vh"

        if (progress < duration) {
            window.requestAnimationFrame(::step)
        }

    }

    window.requestAnimationFrame(::step)
}

fun HTMLElement.imagVisibleOut(duration: Double) {
    val targetOpacity = 0.0
    var start: Double? = null

    fun step(timestamp: Double) {
        if (start == null) start = timestamp
        val progress = timestamp - (start ?: timestamp)
        val opacity = max(1 - progress / duration, targetOpacity)

        style.opacity = opacity.toString()
        style.height = "${getBoundingClientRect().height * opacity}px"

        if (progress < duration) {
            window.requestAnimationFrame(::step)
        }

    }

    window.requestAnimationFrame(::step)
}

fun animateScrollTo(targetY: Double, duration: Double) {
    val startY = window.scrollY
    val startTime = window.performance.now()

    fun step(timestamp: Double) {
        val currentTime = timestamp
        val progress = min((currentTime - startTime) / duration, 1.0)

        fun easing(t: Double): Double {
            return if (t < 0.5) 4 * t * t * t else (t - 1) * (2 * t - 2) * (2 * t - 2) + 1 // Эффект easeInOutCubic
        }

        window.scrollTo(0.0, startY + (targetY - startY) * easing(progress))

        if (progress < 1.0) {
            window.requestAnimationFrame(::step)
        }
    }

    window.requestAnimationFrame(::step)
}