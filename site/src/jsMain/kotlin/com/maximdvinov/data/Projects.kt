package com.maximdvinov.data

import com.maximdvinov.models.LocalizedText
import com.maximdvinov.models.ProjectItem
import com.maximdvinov.models.ProjectLink

object Projects {
    private val items = listOf(
        ProjectItem(
            id = "cross-sync",
            title = "CrossSync",
            short = LocalizedText("Синхронизация буфера обмена между macOS и Android", "Clipboard synchronization between macOS and Android"),
            description = LocalizedText(
                "Kotlin Multiplatform проект для синхронизации буфера обмена между macOS и Android в локальной сети, без облачных сервисов.",
                "A Kotlin Multiplatform project for clipboard synchronization between macOS and Android over a local network, without cloud services.",
            ),
            details = LocalizedText(
                "CrossSync синхронизирует содержимое буфера обмена между Mac и Android-устройством внутри локальной сети. Проект объединяет desktop-приложение для macOS, Android-клиент, QR-подключение, локальную историю буфера обмена с категориями и защищенную LAN-синхронизацию текста.",
                "CrossSync synchronizes clipboard content between Mac and Android over a local network. The project combines a macOS desktop app, an Android client, QR-based pairing, local clipboard history with categories, and protected LAN text synchronization.",
            ),
            role = LocalizedText(
                "Личный Kotlin Multiplatform проект: проектирование общей логики, desktop и Android-клиентов, локального хранения, WebSocket-синхронизации и экрана настроек.",
                "Personal Kotlin Multiplatform project: shared logic, desktop and Android clients, local storage, WebSocket synchronization, and settings UI.",
            ),
            year = "2026",
            createdAt = 202511,
            platform = LocalizedText("macOS, Android", "macOS, Android"),
            status = LocalizedText("В разработке", "In development"),
            stack = listOf("Kotlin Multiplatform", "Compose Multiplatform", "Ktor", "Koin", "Room", "SQLite", "WebSocket"),
            image = "/images/crossync.png",
            gallery = listOf("/images/crossync-1.png", "/images/crossync-2.png"),
            accent = "#1f5c88",
            links = listOf(
                ProjectLink("GitHub", "https://github.com/MaximDvinov/CrossSync", "/images/github.svg"),
                ProjectLink("Website", "https://maximdvinov.github.io/CrossSync/"),
            ),
        ),
        ProjectItem(
            id = "sentilens",
            title = "Sentilens",
            short = LocalizedText("Multiplatform diary app", "Multiplatform diary app"),
            description = LocalizedText(
                "Мобильное приложение для управления заметками: создание, редактирование, сохранение и удаление записей с анализом эмоциональной окраски текста.",
                "A notes management app for creating, editing, saving, and deleting entries with text sentiment analysis.",
            ),
            details = LocalizedText(
                "Sentilens — дипломный проект в формате мультиплатформенного дневника. Пользователь ведет заметки, приложение анализирует эмоциональную окраску текста и предлагает действия, которые могут помочь улучшить настроение.",
                "Sentilens is a graduation project built as a multiplatform diary. Users write notes, the app analyzes the emotional tone of the text, and then suggests actions that may help improve mood.",
            ),
            role = LocalizedText(
                "Разработка клиентской части: общий Kotlin-код, Compose Multiplatform интерфейс, навигация, локальное хранение и интеграция с серверной частью.",
                "Client-side development: shared Kotlin code, Compose Multiplatform UI, navigation, local storage, and integration with the server side.",
            ),
            year = "2023",
            createdAt = 202304,
            platform = LocalizedText("Android, Desktop, JS", "Android, Desktop, JS"),
            status = LocalizedText("Закрыт", "Closed"),
            stack = listOf("Kotlin Multiplatform", "Compose Multiplatform", "Ktor Client", "Realm", "Voyager", "Koin"),
            image = "/images/sentilens.png",
            gallery = listOf("/images/sentilens.png", "/images/sentilens2.png", "/images/sentilens3.png", "/images/sentilens4.png"),
            accent = "#2f7d4c",
            links = listOf(
                ProjectLink("GitHub", "https://github.com/MaximDvinov/Sentilens", "/images/github.svg"),
            ),
        ),
        ProjectItem(
            id = "running-app",
            title = "Run Tracker",
            short = LocalizedText("Простое приложение для бега", "Simple running app"),
            description = LocalizedText(
                "Простое и интуитивно понятное приложение для бега и ходьбы с возможностью самостоятельно формировать интервальные тренировки.",
                "A simple and intuitive app for running and walking with the ability to configure interval workouts manually.",
            ),
            details = LocalizedText(
                "Run Tracker — заказное Android-приложение для бега и ходьбы, опубликованное в RuStore под названием «Просто беги». Основной сценарий — интервальные тренировки, где пользователь сам задает дистанцию или время для каждого этапа.",
                "Run Tracker is a commissioned Android app for running and walking, published on RuStore as “Просто беги”. Its core flow is interval training where users define distance or duration for each stage.",
            ),
            role = LocalizedText(
                "Полный цикл Android-разработки заказного приложения: интерфейс на Jetpack Compose, трекинг тренировок, работа с картами, локальные данные и подготовка к публикации.",
                "Full-cycle Android development for a commissioned app: Jetpack Compose UI, workout tracking, maps, local data, and release preparation.",
            ),
            year = "2023",
            createdAt = 202300,
            platform = LocalizedText("Android", "Android"),
            status = LocalizedText("Завершен", "Completed"),
            stack = listOf("Kotlin", "Jetpack Compose", "MVVM", "Room", "Yandex Maps", "Navigation"),
            image = "/images/run_tracker1.png",
            gallery = listOf("/images/run_tracker1.png", "/images/run_tracker2.png"),
            accent = "#8fc93a",
            links = listOf(
                ProjectLink("RuStore", "https://www.rustore.ru/catalog/app/com.runner.trakcker", "/images/rustore.svg"),
            ),
        ),
        ProjectItem(
            id = "cosmos-info",
            title = "CosmosInfo",
            short = LocalizedText("Клиент для NASA Astronomy Picture of the Day", "Client for NASA Astronomy Picture of the Day"),
            description = LocalizedText(
                "Android-приложение о космосе: показывает фото и видео, связанные с космосом, используя данные сервиса NASA APOD.",
                "An Android app about space: it shows space-related photos and videos using data from NASA APOD.",
            ),
            details = LocalizedText(
                "CosmosInfo — Android-приложение о космосе на базе NASA Astronomy Picture of the Day. Экран показывает фото или видео дня, описание объекта и визуально адаптирует оформление под изображение.",
                "CosmosInfo is an Android app based on NASA Astronomy Picture of the Day. It shows the daily photo or video, object description, and adapts the visual style to the image.",
            ),
            role = LocalizedText(
                "Личный Android-проект: UI на Jetpack Compose, получение данных из API, MVVM-структура, DI, асинхронная работа и динамическая палитра интерфейса.",
                "Personal Android project: Jetpack Compose UI, API data loading, MVVM structure, DI, asynchronous work, and dynamic UI palette.",
            ),
            year = "2022",
            createdAt = 202211,
            platform = LocalizedText("Android", "Android"),
            status = LocalizedText("Завершен", "Completed"),
            stack = listOf("Kotlin", "Jetpack Compose", "Navigation Compose", "MVVM", "Hilt", "Palette", "Flow", "Coroutines"),
            image = "/images/cosmos_info.png",
            gallery = listOf("/images/cosmos_info.png"),
            accent = "#b95677",
            links = listOf(
                ProjectLink("GitHub", "https://github.com/MaximDvinov/CosmosApp", "/images/github.svg"),
            ),
        ),
    )

    val all = items.sortedByDescending { it.createdAt }

    fun byId(id: String): ProjectItem? = all.firstOrNull { it.id == id }
}
