pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TestTask"
include(":app")
include(":feature:login")
include(":core:base")
include(":core:di")
include(":data")
include(":domain")
include(":presentation")
include(":feature:main")
include(":feature:relevantVacancies")
include(":core:navigation")
include(":feature:favoriteVacancies")
include(":feature:login2")
include(":feature:vacancyDetails")
include(":feature:respond")
include(":feature:coverLetter")
include(":core:common")
