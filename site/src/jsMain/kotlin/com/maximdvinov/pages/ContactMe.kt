package com.maximdvinov.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maximdvinov.models.languageFromCode
import com.maximdvinov.ui.PageShell
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext

@Page("contactme")
@Composable
fun ContactMePage(ctx: PageContext) {
    var language by remember { mutableStateOf(languageFromCode(ctx.route.queryParams["lang"])) }

    PageShell {
        PortfolioLanding(
            language = language,
            onLanguageChange = { language = it },
            showHome = true,
            preHero = { ContactBanner() }
        )
    }
}
