package com.project.jf.moneo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform