import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val title: String,
    val subtitle: String,
    val description: String,
    val icon: String? = null,
    val image: List<String>,
    val urls: List<Url>
)

@Serializable
data class Url(
    val url: String,
    val title: String,
    val icon: String
)