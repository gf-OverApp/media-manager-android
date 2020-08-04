package com.overapp.mediaplayerlib.mediaplayer

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

/**
 * Default Media Player
 */
class AndroidMediaPlayer(context: Context) : MediaPlayer(context) {

    /**
     * We declare the view where video will be shown,
     * we're gonna pass it to our Custom PlayerView
     */
    val videoView by lazy {
        VideoView(context)
    }

    var position = 0

    override fun play(
        videoURL: String
    ) {
        setMediaController(context, videoView)
        try {
            Log.i("videoplayer", "Video URL: $videoURL")
            val uri: Uri = Uri.parse(videoURL)
            videoView.setVideoURI(uri)
            videoView.requestFocus()
        } catch (e: Exception) {
            Log.e("videoplayer", "Error Play URL Video: " + e.message)
            Toast.makeText(context, "Error Play URL Video: " + e.message, Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    //We return the view where video will be shown
    override fun getVideoView(): View = videoView

    private fun setMediaController(context: Context?, videoView: VideoView) {
        val mediaController = MediaController(context);

        // Set the videoView that acts as the anchor for the MediaController.
        mediaController.setAnchorView(videoView);

        // Set MediaController for VideoView
        videoView.setMediaController(mediaController);

        // When the video file ready for playback.
        videoView.setOnPreparedListener(android.media.MediaPlayer.OnPreparedListener { mediaPlayer ->
            videoView.seekTo(position)
            if (position == 0) {
                videoView.start()
            }

            // When video Screen change size.
            mediaPlayer.setOnVideoSizeChangedListener { _, _, _ -> // Re-Set the videoView that acts as the anchor for the MediaController
                mediaController.setAnchorView(videoView)
            }

        })
    }

}