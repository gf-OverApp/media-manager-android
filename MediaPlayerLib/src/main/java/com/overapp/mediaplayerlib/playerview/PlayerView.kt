package com.overapp.mediaplayerlib.playerview

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.overapp.mediaplayerlib.R
import com.overapp.mediaplayerlib.mediaplayer.AndroidMediaPlayer
import com.overapp.mediaplayerlib.mediaplayer.ExoPlayer
import com.overapp.mediaplayerlib.mediaplayer.MediaPlayer

/**
 * Custom View, here we will show the video
 */
class PlayerView : FrameLayout {
    val demo_url = "https://raw.githubusercontent.com/o7planning/webexamples/master/_testdatas_/mov_bbb.mp4"

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    lateinit var player: MediaPlayer

    private fun init(attrs: AttributeSet? = null) {
        val tv = TextView(context)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PlayerView,
            0, 0
        ).apply {
            /**
             * Here we get wich player user chose, exoplayer or default?
             */
            val playerChosen =
                getString(R.styleable.PlayerView_playerLib)

            when (playerChosen) {
                "exo_player" -> {
                    //User chose exoplayer, we instantiate it
                    player = ExoPlayer(context)
                }
                else -> {
                    //User chose default player, we instantiate it
                    player = AndroidMediaPlayer(context)
                }
            }
            tv.text = playerChosen
        }
        /**
         * We add the view where video will be played: 
         * if player is ExoPlayer it's gonna be a PlayerView
         * if player is default it's gonna be a VideoView()
         */
        this.addView(player.getVideoView())
        this.addView(tv)

        /**
         * We actually play the video
         */
        player.play(demo_url)
    }
}