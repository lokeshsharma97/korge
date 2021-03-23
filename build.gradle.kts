buildscript {
    val easyPluginVersion: String by project

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        google()
        maven { url = uri("https://dl.bintray.com/korlibs/korlibs/") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-dev") }
    }

    dependencies {
        classpath("com.soywiz.korlibs:easy-kotlin-mpp-gradle-plugin:$easyPluginVersion")
    }
}

allprojects {
    repositories {
        maven {
            url = uri("https://maven.pkg.jetbrains.space/invideo/p/mobile/korgelibs")

            val spaceUsername = project.properties["space.username"] as String?
            val spacePassword = project.properties["space.password"] as String?
            credentials {
                username = spaceUsername
                password = spacePassword
            }
        }
    }
}

val buildExtraGradleFile = File(rootDir, "build.extra.gradle.kts")
if (buildExtraGradleFile.exists()) {
    apply(from = buildExtraGradleFile)
}
