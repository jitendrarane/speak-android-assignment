package com.jitendra.speakhomeassignment.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import java.io.IOException
import javax.inject.Inject

class MockAsrData
    @Inject
    constructor(
        @ApplicationContext val appContext: Context,
    ) {
        fun loadAsrData(): List<AsrStream>? {
            val assetManager = appContext.assets
            val jsonString: String

            try {
                val inputStream = assetManager.open("asr-stream-audio-chunks.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                jsonString = String(buffer)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }

            return Json.decodeFromString<List<AsrStream>>(jsonString)
        }
    }
