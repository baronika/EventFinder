package com.baronika2004.eventfinder

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.baronika2004.eventfinder.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var bindingSignUp:ActivitySignUpBinding

    private val auth:FirebaseAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        bindingSignUp=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(bindingSignUp.root)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        bindingSignUp.buttonSignUp.setOnClickListener {
            val sEmail=bindingSignUp.editTextEmail.text.toString()
            val sPassword=bindingSignUp.editTextPassword.text.toString()
            firebaseSignUp(sEmail,sPassword)
        }
    }


    private fun firebaseSignUp(sEmail:String,sPassword:String) {
        auth.createUserWithEmailAndPassword(sEmail,sPassword).addOnCompleteListener { task->
            if(task.isSuccessful){
                val intent=Intent(this,MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(applicationContext,"Welcome to EventFinder!",Toast.LENGTH_LONG).show()
                finish()
            }
            else{
                Toast.makeText(applicationContext,"Try Again",Toast.LENGTH_LONG).show()
            }
        }
    }
}
