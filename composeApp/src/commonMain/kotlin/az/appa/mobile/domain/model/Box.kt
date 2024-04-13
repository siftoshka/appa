package az.appa.mobile.domain.model

data class Box(
    val id: Int,
    val title: String,
    val subtitle: String,
    val apps: List<UniversalApp>,
)
