package com.baronika2004.eventfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.baronika2004.eventfinder.databinding.ActivityLaunchBinding
import com.baronika2004.eventfinder.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding:ActivityLoginBinding

    private val auth:FirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        loginBinding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loginBinding.buttonLogin.setOnClickListener {
            val lUsername=loginBinding.editTextUsername.text.toString()
            val lPassword=loginBinding.editTextPassword.text.toString()
            firebaseLogin(lUsername,lPassword)
        }
    }

    private fun firebaseLogin(lUsername: String, lPassword: String) {
        auth.signInWithEmailAndPassword(lUsername,lPassword).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Welcome to EventFinder!",Toast.LENGTH_LONG).show()
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()

            }
            else{
                Toast.makeText(applicationContext,"Try Again",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val user=auth.currentUser
        if(user!=null){
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
