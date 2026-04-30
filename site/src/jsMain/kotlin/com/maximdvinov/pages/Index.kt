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
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.layout.SimpleGrid
import com.varabyte.kobweb.silk.components.layout.numColumns
import org.jetbrains.compose.web.css.px

@Page
@Composable
fun IndexPage(ctx: PageContext) {
    var language by remember { mutableStateOf(languageFromCode(ctx.route.queryParams["lang"])) }

    PageShell {
        TopBar(language = language, onLanguageChange = { language = it })
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
}
