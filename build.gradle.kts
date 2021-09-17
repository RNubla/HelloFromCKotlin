plugins {
    kotlin("multiplatform") version "1.5.30"
}

group = "me.loopyelbarto"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
        compilations["main"].cinterops{
            val greeter by creating{
                when(preset){
                    presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
                }
            }

        }
        compilations["main"].cinterops{
            val add by creating{
                when (preset){
                    presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
                }
            }
        }
        compilations["main"].cinterops{
            val subtract by creating{
                when (preset){
                    presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
                }
            }
        }
//        compilations["main"].cinterops{
//            val raylib by creating{
//                when (preset){
//                    presets["linuxX64"] -> includeDirs.headerFilterOnly("/usr/include", "/usr/include/x86_64-linux-gnu")
//                }
//            }
//        }

    }
    sourceSets {
        val nativeMain by getting
        val nativeTest by getting
    }
}
