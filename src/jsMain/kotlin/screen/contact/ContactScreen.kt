package screen.contact

import Screen
import androidx.compose.runtime.Composable
import component.Column

class ContactScreen : Screen {
    override val route = "Contact"
    override var scrollState: Double = 0.0
    @Composable
    override fun content() {
        ContactScreen()
    }
}

@Composable
fun ContactContent() {
    Column {

    }
}