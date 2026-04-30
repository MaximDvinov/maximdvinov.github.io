package com.maximdvinov.models

enum class AppLanguage { RU, EN }

val AppLanguage.code: String
    get() = when (this) {
        AppLanguage.RU -> "ru"
        AppLanguage.EN -> "en"
    }

fun languageFromCode(value: String?): AppLanguage = when (value?.lowercase()) {
    "en" -> AppLanguage.EN
    else -> AppLanguage.RU
}

data class LocalizedText(
    val ru: String,
    val en: String,
) {
    fun get(language: AppLanguage): String = if (language == AppLanguage.RU) ru else en
}

data class ProjectLink(
    val title: String,
    val url: String,
    val icon: String = "",
)

data class ProjectItem(
    val id: String,
    val title: String,
    val short: LocalizedText,
    val description: LocalizedText,
    val details: LocalizedText,
    val role: LocalizedText,
    val year: String,
    val createdAt: Int,
    val platform: LocalizedText,
    val status: LocalizedText,
    val stack: List<String>,
    val image: String,
    val gallery: List<String>,
    val accent: String,
    val links: List<ProjectLink>,
    val featured: Boolean = true,
)
