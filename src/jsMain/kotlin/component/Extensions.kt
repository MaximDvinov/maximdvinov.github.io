package component

import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.w3c.dom.Document
import org.w3c.dom.HTMLLinkElement

@OptIn(ExperimentalComposeWebApi::class)
fun CSSBuilder.transition(property: String, duration: CSSSizeValue<out CSSUnitTime>? = 0.1.s) {
    transitions {
        this.properties(property) {
            this.duration = duration
        }
    }
}

fun Document.changeFavicon(newFaviconUrl: String) {
    val link = querySelector("link[rel='icon']") as? HTMLLinkElement
    if (link != null) {
        link.href = newFaviconUrl
    } else {
        val newLink = createElement("link") as HTMLLinkElement
        newLink.rel = "icon"
        newLink.href = newFaviconUrl
        head?.appendChild(newLink)
    }
}