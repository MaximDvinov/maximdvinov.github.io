package com.maximdvinov.ui

import androidx.compose.runtime.Composable
import com.maximdvinov.i18n.AppStrings
import com.maximdvinov.models.AppLanguage
import com.maximdvinov.models.ProjectItem
import com.maximdvinov.models.code
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
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.size
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.navigation.OpenLinkStrategy
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.navigation.Link
import com.varabyte.kobweb.silk.components.text.SpanText
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.FlexWrap
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.px

@Composable
fun PageShell(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .maxWidth(1180.px)
            .styleModifier {
                property("margin", "0 auto")
                property("padding", "clamp(14px, 4vw, 28px) clamp(12px, 4vw, 24px)")
            },
        verticalArrangement = Arrangement.spacedBy(24.px)
    ) {
        content()
    }
}

@Composable
fun TopBar(language: AppLanguage, onLanguageChange: (AppLanguage) -> Unit, showHome: Boolean = false) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.Forest)
            .borderRadius(18.px)
            .padding(topBottom = 12.px, leftRight = 14.px)
            .gap(10.px)
            .flexWrap(FlexWrap.Wrap)
            .styleModifier {
                property("box-shadow", "0 14px 40px rgba(47, 111, 65, 0.18)")
                property("overflow", "hidden")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.flex("1 1 160px").styleModifier { property("min-width", "0") },
            horizontalArrangement = Arrangement.spacedBy(10.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (showHome) {
                Link(
                    path = "/?lang=${language.code}",
                    text = "←",
                    modifier = Modifier
                        .width(36.px)
                        .height(36.px)
                        .borderRadius(999.px)
                        .backgroundColor(Color("rgba(255,255,255,0.14)"))
                        .color(PortfolioColors.White)
                        .fontSize(20.px)
                        .fontWeight(FontWeight.Bold)
                        .styleModifier {
                            property("display", "inline-flex")
                            property("align-items", "center")
                            property("justify-content", "center")
                            property("text-decoration", "none")
                        }
                )
            }
            Link(
                path = "/?lang=${language.code}",
                text = "MaximDvinov",
                modifier = Modifier
                    .color(PortfolioColors.White)
                    .fontWeight(FontWeight.Bold)
                    .styleModifier {
                        property("font-size", "clamp(24px, 4.8vw, 34px)")
                        property("line-height", "1")
                        property("text-decoration", "none")
                        property("white-space", "nowrap")
                    }
            )
        }

        Row(
            modifier = Modifier
                .flex("0 1 auto")
                .styleModifier { property("min-width", "0") },
            horizontalArrangement = Arrangement.spacedBy(8.px),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageButton("RU", language == AppLanguage.RU) { onLanguageChange(AppLanguage.RU) }
            LanguageButton("EN", language == AppLanguage.EN) { onLanguageChange(AppLanguage.EN) }
            IconLink("https://t.me/MaximDvinov", "/images/tg.svg", "Telegram")
            IconLink("https://github.com/MaximDvinov", "/images/github.svg", "GitHub")
        }
    }
}

@Composable
fun HeroBlock(language: AppLanguage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.Panel)
            .borderRadius(24.px)
            .padding(24.px)
            .gap(20.px)
            .flexWrap(FlexWrap.Wrap)
            .styleModifier {
                property("animation", "fade-up 700ms ease both")
                property("position", "relative")
                property("overflow", "hidden")
                property("padding", "clamp(20px, 6vw, 56px)")
                property("gap", "clamp(14px, 3vw, 32px)")
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.flex("1 1 430px"),
            verticalArrangement = Arrangement.spacedBy(18.px)
        ) {
            SpanText(
                AppStrings.heroKicker.get(language),
                Modifier
                    .backgroundColor(PortfolioColors.White)
                    .borderRadius(999.px)
                    .padding(topBottom = 8.px, leftRight = 14.px)
                    .color(PortfolioColors.Forest)
                    .fontSize(13.px)
                    .fontWeight(FontWeight.Bold)
            )
            SpanText(
                AppStrings.title.get(language),
                Modifier
                    .fontSize(42.px)
                    .fontWeight(FontWeight.Bold)
                    .lineHeight(1.02)
                    .color(PortfolioColors.Ink)
                    .styleModifier { property("font-size", "clamp(34px, 8.2vw, 62px)") }
            )
            SpanText(
                AppStrings.intro.get(language),
                Modifier
                    .fontSize(18.px)
                    .lineHeight(1.45)
                    .fontWeight(FontWeight.Medium)
                    .color(PortfolioColors.Muted)
                    .styleModifier { property("font-size", "clamp(16px, 3.5vw, 22px)") }
            )
            SpanText(
                AppStrings.availability.get(language),
                Modifier
                    .fontSize(16.px)
                    .color(PortfolioColors.ForestDark)
                    .fontWeight(FontWeight.Bold)
            )
        }

        HeroIllustration()
    }
}

@Composable
private fun HeroIllustration() {
    Box(
        modifier = Modifier
            .width(320.px)
            .height(260.px)
            .borderRadius(22.px)
            .backgroundColor(PortfolioColors.PanelSoft)
            .styleModifier {
                property("box-shadow", "inset 0 0 0 1px rgba(23, 76, 39, 0.08)")
                property("animation", "soft-float 5s ease-in-out infinite")
                property("width", "min(320px, 100%)")
                property("height", "clamp(190px, 44vw, 260px)")
            },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(14.px)) {
            Image(
                src = "/images/kotlin_icon.svg",
                description = "Kotlin",
                modifier = Modifier.size(112.px)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(10.px)) {
                listOf("Android", "KMP", "UI").forEach { Chip(it, active = it == "KMP") }
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, text: String? = null) {
    Column(verticalArrangement = Arrangement.spacedBy(8.px)) {
        SpanText(
            title,
            Modifier
                .fontSize(28.px)
                .fontWeight(FontWeight.Bold)
                .lineHeight(1.1)
                .color(PortfolioColors.Ink)
                .styleModifier { property("font-size", "clamp(24px, 6.2vw, 40px)") }
        )
        if (text != null) {
            SpanText(text, Modifier.fontSize(16.px).lineHeight(1.6).color(PortfolioColors.Muted))
        }
    }
}

@Composable
fun ProjectCard(project: ProjectItem, language: AppLanguage, index: Int) {
    Link(
        path = "/projects/${project.id}?lang=${language.code}",
        modifier = Modifier
            .fillMaxWidth()
            .styleModifier {
                property("display", "block")
                property("height", "100%")
                property("text-decoration", "none")
                property("color", "inherit")
            }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .backgroundColor(PortfolioColors.White)
                .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.18)"))
                .borderRadius(20.px)
                .padding(14.px)
                .styleModifier {
                    property("animation", "fade-up 650ms ease both")
                    property("animation-delay", "${index * 90}ms")
                    property("height", "100%")
                    property("box-shadow", "0 14px 36px rgba(18, 32, 21, 0.08)")
                },
            verticalArrangement = Arrangement.spacedBy(14.px)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.px)
                    .backgroundColor(Color(project.accent))
                    .borderRadius(16.px)
                    .overflow(Overflow.Hidden),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    src = project.image,
                    description = project.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.px)
                        .objectFit(ObjectFit.Cover)
                        .styleModifier { property("height", "clamp(210px, 46vw, 280px)") }
                )
            }

            Column(verticalArrangement = Arrangement.spacedBy(8.px)) {
                Row(
                    modifier = Modifier.fillMaxWidth().gap(12.px).flexWrap(FlexWrap.Wrap),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SpanText(project.title, Modifier.fontSize(26.px).fontWeight(FontWeight.Bold).color(PortfolioColors.Ink))
                    SpanText(project.year, Modifier.color(PortfolioColors.Muted).fontSize(14.px).fontWeight(FontWeight.Bold))
                }
                SpanText(project.short.get(language), Modifier.fontSize(17.px).fontWeight(FontWeight.Medium).color(PortfolioColors.Muted).lineHeight(1.45))
                SpanText(project.description.get(language), Modifier.fontSize(15.px).lineHeight(1.55).color(PortfolioColors.Muted))
            }

            Row(modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(8.px)) {
                project.stack.take(5).forEachIndexed { stackIndex, item ->
                    Chip(item, active = stackIndex == 0)
                }
            }
        }
    }
}

@Composable
fun Chip(text: String, active: Boolean = false) {
    SpanText(
        text,
        Modifier
            .backgroundColor(if (active) PortfolioColors.Forest else PortfolioColors.Mint)
            .color(if (active) PortfolioColors.White else PortfolioColors.Ink)
            .border(1.px, LineStyle.Solid, if (active) PortfolioColors.ForestDark else Color("rgba(47,111,65,0.18)"))
            .borderRadius(999.px)
            .padding(topBottom = 8.px, leftRight = 14.px)
            .fontSize(13.px)
            .fontWeight(FontWeight.Bold)
            .styleModifier {
                property("white-space", "nowrap")
            }
    )
}

@Composable
fun TechnologyPanel(language: AppLanguage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.Forest)
            .borderRadius(20.px)
            .padding(24.px)
            .styleModifier { property("animation", "fade-up 700ms ease both") },
        verticalArrangement = Arrangement.spacedBy(20.px)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.px)) {
            SpanText(
                AppStrings.technologies.get(language),
                Modifier
                    .fontSize(28.px)
                    .fontWeight(FontWeight.Bold)
                    .lineHeight(1.1)
                    .color(PortfolioColors.White)
                    .styleModifier { property("font-size", "clamp(28px, 6vw, 42px)") }
            )
            SpanText(
                AppStrings.technologyLead.get(language),
                Modifier
                    .fontSize(16.px)
                    .lineHeight(1.6)
                    .color(Color("rgba(255,255,255,0.78)"))
            )
        }
        Row(modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(10.px)) {
            coreTechnologies.forEach { item ->
                SpanText(
                    item,
                    Modifier
                        .backgroundColor(PortfolioColors.White)
                        .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.18)"))
                        .color(PortfolioColors.Ink)
                        .borderRadius(10.px)
                        .padding(topBottom = 14.px, leftRight = 20.px)
                        .fontSize(18.px)
                        .fontWeight(FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun Footer(language: AppLanguage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .backgroundColor(PortfolioColors.PanelSoft)
            .borderRadius(20.px)
            .padding(20.px)
            .gap(20.px)
            .flexWrap(FlexWrap.Wrap),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.flex("1 1 360px"),
            verticalArrangement = Arrangement.spacedBy(8.px)
        ) {
            SpanText(AppStrings.contacts.get(language), Modifier.fontSize(26.px).fontWeight(FontWeight.Bold).color(PortfolioColors.Ink))
            SpanText(AppStrings.contactLine.get(language), Modifier.color(PortfolioColors.Muted).fontSize(16.px).lineHeight(1.55))
        }
        Row(modifier = Modifier.flexWrap(FlexWrap.Wrap).gap(10.px)) {
            ContactPill(AppStrings.writeTelegram.get(language), "https://t.me/MaximDvinov", "/images/tg.svg")
            ContactPill(AppStrings.email.get(language), "mailto:max.dvinov@gmail.com")
            ContactPill(AppStrings.openGithub.get(language), "https://github.com/MaximDvinov", "/images/github.svg")
            ContactPill(AppStrings.openKwork.get(language), "https://kwork.ru/user/maximdvinov")
        }
    }
}

@Composable
fun ContactPill(text: String, url: String, icon: String = "") {
    Link(
        path = url,
        openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
        modifier = Modifier
            .backgroundColor(PortfolioColors.White)
            .border(1.px, LineStyle.Solid, Color("rgba(47,111,65,0.2)"))
            .borderRadius(999.px)
            .padding(topBottom = 10.px, leftRight = 16.px)
            .color(PortfolioColors.Ink)
            .fontWeight(FontWeight.Bold)
            .styleModifier {
                property("display", "inline-flex")
                property("align-items", "center")
                property("gap", "8px")
                property("min-height", "46px")
                property("text-decoration", "none")
            }
    ) {
        if (icon.isNotBlank()) {
            Box(
                modifier = Modifier
                    .size(28.px)
                    .backgroundColor(PortfolioColors.Forest)
                    .borderRadius(999.px),
                contentAlignment = Alignment.Center
            ) {
                Image(src = icon, description = text, modifier = Modifier.size(18.px))
            }
        }
        SpanText(text, Modifier.fontSize(15.px).lineHeight(1.2))
    }
}

@Composable
private fun IconLink(url: String, icon: String, description: String) {
    Link(
        path = url,
        openExternalLinksStrategy = OpenLinkStrategy.IN_NEW_TAB,
        modifier = Modifier
            .width(36.px)
            .height(36.px)
            .backgroundColor(Color("rgba(255,255,255,0.14)"))
            .border(1.px, LineStyle.Solid, Color("rgba(255,255,255,0.28)"))
            .borderRadius(999.px)
            .styleModifier {
                property("display", "inline-flex")
                property("align-items", "center")
                property("justify-content", "center")
            }
    ) {
        Image(src = icon, description = description, modifier = Modifier.size(16.px))
    }
}

@Composable
private fun LanguageButton(label: String, active: Boolean, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .backgroundColor(if (active) PortfolioColors.White else Color("rgba(255,255,255,0.12)"))
            .color(if (active) PortfolioColors.Forest else PortfolioColors.White)
            .border(1.px, LineStyle.Solid, Color("rgba(255,255,255,0.32)"))
            .borderRadius(999.px)
            .padding(topBottom = 2.px, leftRight = 2.px)
            .width(40.px)
            .height(36.px)
            .styleModifier { property("cursor", "pointer") }
    ) {
        SpanText(label, Modifier.fontWeight(FontWeight.Bold))
    }
}
