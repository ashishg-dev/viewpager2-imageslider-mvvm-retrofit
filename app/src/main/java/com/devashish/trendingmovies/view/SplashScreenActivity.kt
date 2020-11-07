package com.devashish.trendingmovies.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.devashish.trendingmovies.R

class SplashScreenActivity : AppCompatActivity() {

    private var progressStatus = 0
    private var handler: Handler = Handler()
    private val incrementProgressValueBy = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startProgress()
    }

    private fun startProgress() {
        progressStatus = 0
        Thread {
            while (progressStatus < 100) {
                progressStatus += incrementProgressValueBy
                handler.post {

                    if (progressStatus >= 100) {
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }

}