val sentilens = Project(
    title = "Sentilens",
    subtitle = "multiplatform note app",
    description = "Sentilens - это мобильное приложение для управления заметками, которое поможет вам оставаться на позитивной волне. Создавайте, редактируйте, сохраняйте и удаляйте заметки. Главной особенностью Sentilens является его способность анализировать эмоциональную окраску текста в ваших заметках. После анализа, Sentilens предложит вам способы улучшения вашего настроения на основе обнаруженных эмоций в заметке. Например, если вы написали заметку, содержащую грустные эмоции, приложение может предложить послушать музыку, почитать что-то интересное или заняться любимым делом.",
    image = listOf(
        "images/sentilens.png",
        "images/sentilens2.png",
        "images/sentilens3.png",
        "images/sentilens4.png",
        "images/sentilens5.png",
    ),
    urls = listOf(
        Url(
            icon = "/images/android.svg",
            title = "Android",
            url = "https://github.com/MaximDvinov/Sentilens/releases/download/1.0.0/SentilensAndroid.apk"
        ), Url(
            icon = "/images/windows.svg",
            title = "Windows",
            url = "https://github.com/MaximDvinov/Sentilens/releases/download/1.0.0/SentilensWindows.msi"
        ), Url(
            icon = "/images/github.svg", title = "GitHub", url = "https://github.com/MaximDvinov/Sentilens"
        )
    ),
    icon = "images/sentilens_logo.svg"
)

val cosmos = Project(
    title = "CosmosInfo",
    subtitle = "Astronomy Picture of the Day Archive",
    description = "Discover the cosmos! Each day a different image or photograph of our fascinating universe is featured, along with a brief explanation written by a professional astronomer. \n" + "\n" + "All this is thanks to the \"Astronomy Picture of the Day (APOD)\" service from NASA. \n",
    image = listOf(
        "images/cosmos_info.png",
    ),
    urls = listOf(
        Url(
            icon = "/images/github.svg", title = "GitHub", url = "https://github.com/MaximDvinov/CosmosApp"
        ), Url(
            icon = "/images/playmarket.svg",
            title = "PlayMarket",
            url = "https://play.google.com/store/apps/details?id=com.dvinov.myspaceapp"
        )
    ),
    icon = "images/cosmos_info_logo.svg"
)

val runTracker = Project(
    title = "Run Tracker",
    subtitle = "Run Tracker - a simple app for running",
    description = "A simple and intuitive app for running and walking. A distinctive feature is the ability to form interval training for running and walking for yourself! Choose the distance or time for each of the training intervals yourself. Suitable for beginners to run and those who are recovering form.",
    image = listOf(
        "images/run_tracker1.png",
        "images/run_tracker2.png",
    ),
    urls = listOf(
        Url(
            icon = "/images/rustore.svg", url = "https://apps.rustore.ru/app/com.runner.trakcker", title = "Rustore"
        )
    ),
    icon = "images/run_tracker_logo.svg"
)
