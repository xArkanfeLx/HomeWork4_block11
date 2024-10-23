package com.example.saveinstancestate

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    lateinit var personViewModel: PersonViewModel
    private lateinit var adapter:ArrayAdapter<Person>

    private var persons:MutableList<Person> = mutableListOf()

    private lateinit var nameET:EditText
    private lateinit var familyET:EditText
    private lateinit var addressET:EditText
    private lateinit var phoneET:EditText

    private lateinit var saveBTN:Button

    private lateinit var personsLV:ListView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        personViewModel = ViewModelProvider(this)[PersonViewModel::class.java]

        nameET = findViewById(R.id.nameET)
        familyET = findViewById(R.id.familyET)
        addressET = findViewById(R.id.addressET)
        phoneET = findViewById(R.id.phoneET)
        saveBTN = findViewById(R.id.saveBTN)
        personsLV = findViewById(R.id.personsLV)

        setNewAdapter()

        personViewModel.adapter.observe(this,Observer{
            persons = it
            setNewAdapter()
        })
        adapter.notifyDataSetChanged()

        saveBTN.setOnClickListener{
            val name = nameET.text
            val family = familyET.text
            val address = addressET.text
            val phone = phoneET.text
            if(name.isEmpty() || family.isEmpty() || address.isEmpty() || phone.isEmpty()) return@setOnClickListener
            val person = Person(name.toString(),family.toString(),address.toString(),phone.toString())
            persons.add(person)
            personViewModel.adapter.value = persons
            adapter.notifyDataSetChanged()

            name.clear()
            family.clear()
            address.clear()
            phone.clear()
        }

        personsLV.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val person = persons[id.toInt()]
            intent = Intent(this,SecondActivity::class.java)
            intent.putExtra(Person::class.java.simpleName,person)
            startActivity(intent)
        }

    }

    private fun setNewAdapter(){
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,persons)
        personsLV.adapter = adapter
    }
}
