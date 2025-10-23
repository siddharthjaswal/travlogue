package com.aurora.travlogue.core.common

import kotlin.random.Random

/**
 * Generates a UUID string
 * KMP-compatible UUID generation
 */
fun generateUUID(): String {
    val randomBytes = Random.Default.nextBytes(16)
    randomBytes[6] = ((randomBytes[6].toInt() and 0x0f) or 0x40).toByte() // Version 4
    randomBytes[8] = ((randomBytes[8].toInt() and 0x3f) or 0x80).toByte() // Variant 10

    return buildString(36) {
        for (i in randomBytes.indices) {
            if (i == 4 || i == 6 || i == 8 || i == 10) append('-')
            append(randomBytes[i].toUByte().toString(16).padStart(2, '0'))
        }
    }
}
