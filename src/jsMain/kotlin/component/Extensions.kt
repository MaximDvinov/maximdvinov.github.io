package component

import kotlinx.browser.localStorage
import kotlinx.serialization.Serializable
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.w3c.dom.Document
import org.w3c.dom.HTMLLinkElement
import org.w3c.dom.Storage
import kotlin.js.Date

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

@Serializable
data class DataWithExpiration(val data: String, val expiration: Double)

fun Storage.setDataWithExpiration(key: String, data: String, expirationMinutes: Int) {
    val date = Date().getTime() + expirationMinutes * 60 * 1000
    setItem(
        key, JSON.stringify(
            DataWithExpiration(
                data, date
            )
        )
    )
}

fun Storage.getDataWithExpiration(key: String): String? {
    val itemStr = localStorage.getItem(key) ?: return null
    val item = JSON.parse<DataWithExpiration>(itemStr)
    val now = Date()
    if (now.getTime() > item.expiration) {
        localStorage.removeItem(key)
        return null
    }
    return item.data
}

fun Document.setCookieWithExpiration(name: String, value: String, expirationMinutes: Int) {
    val expires = Date(expirationMinutes * 60 * 1000)
    val expiresFormatted = expires.toUTCString()

    cookie = "name=$name; value=$value; expires=$expiresFormatted; path=/"
}

fun Document.getCookie(name: String): String? {
    val nameEQ = "name=$name"
    val ca = cookie.split(';')
    for (c in ca) {
        var cookie = c
        while (cookie.startsWith(" ")) {
            cookie = cookie.substring(1)
        }
        if (cookie.startsWith(nameEQ)) {
            return cookie.substring(nameEQ.length)
        }
    }
    return null
}