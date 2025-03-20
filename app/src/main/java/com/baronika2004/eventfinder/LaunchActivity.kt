
package com.baronika2004.eventfinder

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.baronika2004.eventfinder.databinding.ActivityLaunchBinding

class LaunchActivity : AppCompatActivity() {

    lateinit var launchBinding:ActivityLaunchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        launchBinding=ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(launchBinding.root)

        buttonGetStarted()
        buttonButtonLogin()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun buttonButtonLogin() {
        launchBinding.buttonLogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun buttonGetStarted() {
        launchBinding.buttonStart.setOnClickListener {
            val intent=Intent(this@LaunchActivity,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
