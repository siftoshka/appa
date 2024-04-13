package az.appa.mobile.domain.model

data class UniversalApp(
    val id: Int = 0,
    val title: String = "Test",
    val subtitle: String = "Test",
    val link: String = "https://play.google.com/store/apps/details?id=com.asolutions.akart&hl=en&gl=US",
    val imagePath: String = "https://play-lh.googleusercontent.com/UzcCjeMvjpCZ_8L7EocoKi6kZSk6_fXKwk8xxRbBp8bmLUL5RTv4Qn8P_WUL5Uqk=w480-h960-rw",
)
