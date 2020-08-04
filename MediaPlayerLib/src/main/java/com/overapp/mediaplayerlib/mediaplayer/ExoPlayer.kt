package com.overapp.mediaplayerlib.mediaplayer

import android.content.Context
import android.net.Uri
import android.view.View
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

/**
 * Exoplayer
 */
class ExoPlayer(context: Context) : MediaPlayer(context) {

    /**
     * We declare the view where video will be shown and that we're gonna pass
     * to our Custom PlayerView
     */
    val playerView: PlayerView by lazy {
        PlayerView(context)
    }


    override fun play(videoURL: String) {
        /**
         * We setup player and start playing the video as explained in
         * Exoplayer documentation
         */
        val player = SimpleExoPlayer.Builder(context).build()
        //Bind player to the view
        playerView.player = player
        // Produces DataSource instances through which media data is loaded.
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "yourApplicationName")
        )
        // This is the MediaSource representing the media to be played.
        val videoSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(videoURL))
        // Prepare the player with the source.
        player.prepare(videoSource)
        player.playWhenReady = true
    }

    // We return the view where the video will be shown
    override fun getVideoView(): View = playerView
}