rootProject.name = "Movie App"

// Accessing definitions from buildSrc in settings scripts was deprecated in Gradle 5.6.

// Previously, the buildSrc project was built before applying the project’s settings script and its classes
// were visible within the script. Now, buildSrc is built after the settings script and its classes are not
// visible to it. The buildSrc classes remain visible to project build scripts and script plugins.

include(
    ":app",
    ":core",
    ":features:main",
    ":features:detail",
    ":features:discover",
    ":features:genres",
    ":features:trailerVideo",
    ":features:userReviews",
    ":features:iam"
)
