package com.maximdvinov.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maximdvinov.data.Projects
import com.maximdvinov.i18n.AppStrings
import com.maximdvinov.models.AppLanguage
import com.maximdvinov.models.languageFromCode
import com.maximdvinov.ui.Footer
import com.maximdvinov.ui.HeroBlock
import com.maximdvinov.ui.PageShell
import com.maximdvinov.ui.ProjectCard
import com.maximdvinov.ui.SectionHeader
import com.maximdvinov.ui.TechnologyPanel
import com.maximdvinov.ui.TopBar
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun IndexPage(ctx: PageContext) {
    var language by remember { mutableStateOf(languageFromCode(ctx.route.queryParams["lang"])) }

    PageShell {
        PortfolioLanding(language = language, onLanguageChange = { language = it })
    }
}

@Composable
fun PortfolioLanding(
    language: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    showHome: Boolean = false,
    preHero: @Composable (() -> Unit)? = null,
) {
    TopBar(language = language, onLanguageChange = onLanguageChange, showHome = showHome)
    preHero?.invoke()
    HeroBlock(language)

    Column(verticalArrangement = Arrangement.spacedBy(18.px)) {
        SectionHeader(
            title = AppStrings.selectedProjects.get(language),
        )

        SimpleGrid(numColumns(base = 1, md = 2), Modifier.gap(26.px)) {
            Projects.all.forEachIndexed { index, project ->
                ProjectCard(project = project, language = language, index = index)
            }
        }
    }

    TechnologyPanel(language)
    Footer(language)
}

@Composable
fun ContactBanner() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(Color("rgba(47,111,65,0.14)"))
            .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.2)"))
            .borderRadius(18.px)
            .padding(18.px),
        verticalArrangement = Arrangement.spacedBy(12.px),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .backgroundColor(Color("#2f6f41"))
                .borderRadius(999.px)
                .padding(topBottom = 10.px, leftRight = 16.px)
                .gap(10.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                src = "/images/tg.svg",
                description = "Telegram",
                modifier = Modifier.styleModifier {
                    property("width", "16px")
                    property("height", "16px")
                }
            )
            SpanText("Свяжитесь со мной в Telegram", Modifier.color(Color.white).fontWeight(FontWeight.Bold).fontSize(16.px))
        }

        Link(
            path = "https://t.me/MaximDvinov",
            text = "Открыть Telegram",
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
            modifier = Modifier
                .backgroundColor(Color.white)
                .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.22)"))
                .borderRadius(999.px)
                .padding(topBottom = 10.px, leftRight = 16.px)
                .color(Color("#122015"))
                .fontWeight(FontWeight.Bold)
                .styleModifier { property("text-decoration", "none") }
        )
    }
}
