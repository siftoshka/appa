package az.appa.mobile.utils

enum class BoxManagerState {
    CREATE,
    EDIT,
    VIEW;

    companion object {
        fun fromValue(value: String): BoxManagerState = when (value) {
            CREATE.name -> CREATE
            EDIT.name -> EDIT
            VIEW.name -> VIEW
            else -> throw IllegalArgumentException()
        }
    }
}