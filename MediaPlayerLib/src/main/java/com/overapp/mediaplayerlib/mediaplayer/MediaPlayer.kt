package com.overapp.mediaplayerlib.mediaplayer

import android.content.Context
import android.view.View

/**
 * this class is gonna be the blueprint for classes that actually implement libraries:
 * it's important to declare every method we think we'll use
 */
abstract class MediaPlayer(val context: Context) {

    abstract fun play(videoURL: String)

    abstract fun getVideoView(): View
}