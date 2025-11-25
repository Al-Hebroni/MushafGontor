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


        // --- INI KUNCI UNTUK PDF VIEWER ---
        // Menambahkan gudang JitPack agar library PDF bisa ditemukan
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Mushaf Gontor"
include(":app")