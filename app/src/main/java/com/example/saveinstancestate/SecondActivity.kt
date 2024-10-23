package com.example.saveinstancestate

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    var person:Person? = null

    private lateinit var nameTV: TextView
    private lateinit var familyTV: TextView
    private lateinit var addressTV: TextView
    private lateinit var phoneTV: TextView

    private lateinit var returnBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        person = intent.extras?.getSerializable(Person::class.java.simpleName) as Person?

        nameTV = findViewById(R.id.nameTV)
        familyTV = findViewById(R.id.familyTV)
        addressTV = findViewById(R.id.addressTV)
        phoneTV = findViewById(R.id.phoneTV)
        returnBTN = findViewById(R.id.returnBTN)

        nameTV.text = person?.name
        familyTV.text = person?.family
        addressTV.text = person?.address
        phoneTV.text = person?.phone

        returnBTN.setOnClickListener{
            finish()
        }
    }
}