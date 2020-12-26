object Modules {
    const val app = ":app"
    const val core = ":core"
    private const val feature = ":features"

    object Features {
        const val main = "$feature:main"
        const val userReviews = "$feature:userReviews"
        const val trailerVideo = "$feature:trailerVideo"
        const val discover = "$feature:discover"
        const val genres = "$feature:genres"
        const val detail = "$feature:detail"
    }
}