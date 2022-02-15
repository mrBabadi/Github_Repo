package com.bbd.github_repo.util

fun String.isWhiteSpace(): Boolean {
    this.map {
        if (it.isWhitespace()) {
            return true
        }
    }
    return false
}