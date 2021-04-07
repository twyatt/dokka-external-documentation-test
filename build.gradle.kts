buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("multiplatform") version "1.4.31" apply false
    id("org.jetbrains.dokka") version "1.4.30"
}

allprojects {
    repositories {
        mavenCentral()
        jcenter {
            content {
                // https://youtrack.jetbrains.com/issue/IDEA-261387
                includeModule("org.jetbrains.trove4j", "trove4j")

                // https://github.com/Kotlin/kotlinx.html/issues/173
                includeModule("org.jetbrains.kotlinx", "kotlinx-html-jvm")
            }
        }
    }

    tasks.withType<org.jetbrains.dokka.gradle.DokkaTask>().configureEach {
        dokkaSourceSets {
            configureEach {
                externalDocumentationLink(
                    url = "https://juullabs.github.io/koap/docs/",
                    packageListUrl = "https://juullabs.github.io/koap/docs/koap/package-list"
                )
            }
        }
    }
}

tasks.dokkaHtmlMultiModule.configure {
    outputDirectory.set(buildDir.resolve("dokkaHtmlMultiModule"))
}

task<Copy>("assembleGitHubPages") {
    description = "Assembles content for GitHub Pages."
    group = "Build"

    dependsOn(":example1:dokkaHtml")
    dependsOn(":example2:dokkaHtml")
    dependsOn(":dokkaHtmlMultiModule")

    into("$buildDir/gh-pages")

    into("example1") {
        from("${project(":example1").buildDir}/dokka/html") {
            include("**")
        }
    }

    into("example2") {
        from("${project(":example2").buildDir}/dokka/html") {
            include("**")
        }
    }

    into("multimodule") {
        from("$buildDir/dokkaHtmlMultiModule") {
            include("**")
        }
    }
}
