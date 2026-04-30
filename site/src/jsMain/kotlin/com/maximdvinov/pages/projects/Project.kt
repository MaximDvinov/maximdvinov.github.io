package com.maximdvinov.pages.projects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.maximdvinov.data.Projects
import com.maximdvinov.i18n.AppStrings
import com.maximdvinov.models.AppLanguage
import com.maximdvinov.models.ProjectItem
import com.maximdvinov.models.code
import com.maximdvinov.models.languageFromCode
import com.maximdvinov.ui.Chip
import com.maximdvinov.ui.ContactPill
import com.maximdvinov.ui.Footer
import com.maximdvinov.ui.PageShell
import com.maximdvinov.ui.PortfolioColors
import com.maximdvinov.ui.SectionHeader
import com.maximdvinov.ui.TopBar
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.backgroundColor
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flex
import com.varabyte.kobweb.compose.ui.modifiers.flexWrap
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

@Page("{id}")
@Composable
fun ProjectPage(ctx: PageContext) {
    var language by remember { mutableStateOf(languageFromCode(ctx.route.queryParams["lang"])) }
    val project = Projects.byId(ctx.route.params["id"].orEmpty())

    PageShell {
        TopBar(language = language, onLanguageChange = { language = it }, showHome = true)

        if (project == null) {
            Column(verticalArrangement = Arrangement.spacedBy(16.px)) {
                SectionHeader(
                    title = AppStrings.projectNotFound.get(language),
                    text = AppStrings.projectNotFoundHint.get(language),
                )
                Link(path = "/?lang=${language.code}", text = AppStrings.backToHome.get(language))
            }
        } else {
            ProjectHero(project, language)
            ProjectContent(project, language)
            ProjectGallery(project, language)
            Footer(language)
        }
    }
}

@Composable
private fun ProjectHero(project: ProjectItem, language: AppLanguage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.Panel)
            .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.16)"))
            .borderRadius(24.px)
            .padding(24.px)
            .gap(18.px)
            .flexWrap(FlexWrap.Wrap)
            .styleModifier {
                property("animation", "fade-up 650ms ease both")
                property("box-shadow", "0 20px 55px rgba(18, 32, 21, 0.12)")
                property("padding", "clamp(18px, 5vw, 36px)")
                property("gap", "clamp(14px, 3.4vw, 34px)")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.flex("1 1 440px"),
            verticalArrangement = Arrangement.spacedBy(18.px)
        ) {
            SpanText(
                project.year,
                Modifier.color(PortfolioColors.Forest).fontWeight(FontWeight.Bold).fontSize(15.px)
            )
            SpanText(
                project.title,
                Modifier
                    .fontSize(42.px)
                    .fontWeight(FontWeight.Bold)
                    .lineHeight(1.02)
                    .color(PortfolioColors.Ink)
                    .styleModifier { property("font-size", "clamp(34px, 8vw, 58px)") }
            )
            SpanText(
                project.short.get(language),
                Modifier
                    .fontSize(19.px)
                    .fontWeight(FontWeight.Medium)
                    .color(PortfolioColors.Muted)
                    .lineHeight(1.45)
                    .styleModifier { property("font-size", "clamp(16px, 4.4vw, 24px)") }
            )
            SpanText(
                project.description.get(language),
                Modifier
                    .fontSize(16.px)
                    .lineHeight(1.65)
                    .color(PortfolioColors.Muted)
            )
            Row(modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(10.px)) {
                project.links.forEach { link ->
                    ContactPill(link.title, link.url, link.icon)
                }
            }
        }

        Link(
            path = project.image,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
            modifier = Modifier
                .flex("1 1 400px")
                .maxWidth(540.px)
                .styleModifier {
                    property("display", "block")
                    property("text-decoration", "none")
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.px)
                    .borderRadius(20.px)
                    .overflow(Overflow.Hidden)
                    .backgroundColor(Color(project.accent)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    src = project.image,
                    description = project.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.px)
                        .objectFit(ObjectFit.Cover)
                        .styleModifier { property("height", "clamp(220px, 52vw, 330px)") }
                )
            }
        }
    }
}

@Composable
private fun ProjectContent(project: ProjectItem, language: AppLanguage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(1080.px)
            .styleModifier { property("margin", "0 auto") },
        verticalArrangement = Arrangement.spacedBy(24.px)
    ) {
        SimpleGrid(numColumns(base = 1, lg = 3), Modifier.gap(24.px)) {
            Column(
                modifier = detailCardModifier()
                    .styleModifier {
                        property("grid-column", "span 2")
                        property("height", "100%")
                    },
                verticalArrangement = Arrangement.spacedBy(24.px)
            ) {
                TextBlock(AppStrings.aboutProject.get(language), project.details.get(language))
                TextBlock(AppStrings.myRole.get(language), project.role.get(language))
            }

            Column(verticalArrangement = Arrangement.spacedBy(24.px)) {
                ProjectFacts(project, language)

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(18.px)
                ) {
                    Row(modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(10.px)) {
                        project.stack.forEach { item -> Chip(item, active = true) }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectGallery(project: ProjectItem, language: AppLanguage) {
    var selectedIndex by remember(project.id) { mutableStateOf(0) }
    val images = project.gallery.ifEmpty { listOf(project.image) }
    val selectedImage = images[selectedIndex.coerceIn(0, images.lastIndex)]

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(1080.px)
            .styleModifier { property("margin", "0 auto") },
        verticalArrangement = Arrangement.spacedBy(16.px)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().flexWrap(FlexWrap.Wrap).gap(16.px),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SectionHeader(AppStrings.gallery.get(language))
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.px),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CarouselButton("←") {
                    selectedIndex = if (selectedIndex == 0) images.lastIndex else selectedIndex - 1
                }
                SpanText(
                    "${selectedIndex + 1} / ${images.size}",
                    Modifier
                        .backgroundColor(PortfolioColors.White)
                        .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.18)"))
                        .borderRadius(999.px)
                        .padding(topBottom = 10.px, leftRight = 14.px)
                        .fontSize(14.px)
                        .fontWeight(FontWeight.Bold)
                        .color(PortfolioColors.Ink)
                        .styleModifier {
                            property("min-width", "64px")
                            property("height", "52px")
                            property("display", "inline-flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                            property("font-size", "clamp(13px, 3.3vw, 14px)")
                        }
                )
                CarouselButton("→") {
                    selectedIndex = if (selectedIndex == images.lastIndex) 0 else selectedIndex + 1
                }
            }
        }

        Link(
            path = selectedImage,
            openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
            modifier = Modifier
                .fillMaxWidth()
                .styleModifier {
                    property("display", "block")
                    property("text-decoration", "none")
                }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .backgroundColor(PortfolioColors.White)
                    .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.18)"))
                    .borderRadius(22.px)
                    .overflow(Overflow.Hidden)
                    .styleModifier { property("box-shadow", "0 20px 54px rgba(18, 32, 21, 0.1)") },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    src = selectedImage,
                    description = project.title,
                    modifier = Modifier.fillMaxWidth().objectFit(ObjectFit.Contain)
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .gap(12.px)
                .flexWrap(FlexWrap.Wrap),
            horizontalArrangement = Arrangement.spacedBy(12.px, Alignment.CenterHorizontally)
        ) {
            images.forEachIndexed { index, image ->
                Button(
                    onClick = { selectedIndex = index },
                    modifier = Modifier
                        .backgroundColor(if (index == selectedIndex) PortfolioColors.PanelSoft else PortfolioColors.White)
                        .border(
                            1.px,
                            LineStyle.Solid,
                            if (index == selectedIndex) PortfolioColors.Forest else Color("rgba(47,111,65,0.18)")
                        )
                        .borderRadius(14.px)
                        .padding(6.px)
                        .styleModifier {
                            property("cursor", "pointer")
                            property("width", "138px")
                            property("height", "88px")
                        }
                ) {
                    Image(
                        src = image,
                        description = project.title,
                        modifier = Modifier.fillMaxWidth().height(74.px).borderRadius(10.px)
                            .objectFit(ObjectFit.Cover)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProjectFacts(project: ProjectItem, language: AppLanguage) {
    Column(
        modifier = Modifier.fillMaxWidth().styleModifier { property("height", "100%") },
        verticalArrangement = Arrangement.spacedBy(16.px)
    ) {
        FactRow(AppStrings.year.get(language), project.year)
        FactRow(AppStrings.platform.get(language), project.platform.get(language))
        FactRow(AppStrings.status.get(language), project.status.get(language))
    }
}

@Composable
private fun FactRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.PanelSoft)
            .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.14)"))
            .borderRadius(14.px)
            .padding(14.px)
            .gap(14.px)
            .flexWrap(FlexWrap.Wrap),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SpanText(
            label,
            Modifier.fontSize(14.px).fontWeight(FontWeight.Bold).color(PortfolioColors.Muted)
        )
        SpanText(
            value,
            Modifier.fontSize(17.px).fontWeight(FontWeight.Bold).color(PortfolioColors.Ink)
                .lineHeight(1.35)
        )
    }
}

@Composable
private fun CarouselButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .backgroundColor(PortfolioColors.Forest)
            .color(PortfolioColors.White)
            .border(1.px, LineStyle.Solid, PortfolioColors.ForestDark)
            .borderRadius(999.px)
            .width(52.px)
            .height(52.px)
            .padding(0.px)
            .fontSize(18.px)
            .fontWeight(FontWeight.Bold)
            .styleModifier {
                property("cursor", "pointer")
                property("display", "inline-flex")
                property("align-items", "center")
                property("justify-content", "center")
            }
    ) {
        SpanText(text)
    }
}

private fun detailCardModifier(): Modifier = Modifier
    .fillMaxWidth()
    .backgroundColor(PortfolioColors.White)
    .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.18)"))
    .borderRadius(20.px)
    .padding(22.px)
    .styleModifier { property("box-shadow", "0 16px 42px rgba(18, 32, 21, 0.08)") }

@Composable
private fun TextBlock(title: String, text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(10.px)) {
        SpanText(
            title,
            Modifier
                .fontSize(26.px)
                .fontWeight(FontWeight.Bold)
                .color(PortfolioColors.Ink)
                .styleModifier { property("font-size", "clamp(24px, 6vw, 36px)") }
        )
        SpanText(
            text,
            Modifier
                .fontSize(16.px)
                .lineHeight(1.7)
                .color(PortfolioColors.Muted)
                .styleModifier { property("font-size", "clamp(15px, 3.6vw, 18px)") }
        )
    }
}
