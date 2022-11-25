package com.example.ficheetudiant

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var textinputname: TextInputLayout
    lateinit var nom: EditText
    lateinit var textinputdate: TextInputLayout
    lateinit var dtn: EditText
    lateinit var setListener: OnDateSetListener
    lateinit var textinputmail: TextInputLayout
    lateinit var email: EditText
    lateinit var spinner: Spinner
    lateinit var btn1: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nom = findViewById(R.id.nom)

        dtn = findViewById(R.id.date)
        dtn.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(this,
                { view, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dtn.setText(dat)
                }, year, month, day
            )
            datePickerDialog.show()
        }


        email = findViewById(R.id.email)


        val languages = resources.getStringArray(R.array.classe)
        spinner = findViewById(R.id.spinner)
        var arrayAdapter = ArrayAdapter.createFromResource(
            this, R.array.classe,
            android.R.layout.simple_spinner_dropdown_item
        )
        spinner.adapter = arrayAdapter


        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            if (nom.text.length != 0 && dtn.text.length != 0 && email.text.length != 0) {

                val builder = AlertDialog.Builder(this)
                builder.setMessage("Les données sont enregistrées avec succes")
                builder.setTitle("confirm")
                builder.setIcon(android.R.drawable.stat_sys_download_done)
                builder.setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(applicationContext, ShareFicheEtudiant::class.java)
                    val name = nom.text.toString()
                    intent.putExtra("nom", name)
                    val date = dtn.text.toString()
                    intent.putExtra("dtn", date)
                    val mail = email.text.toString()
                    intent.putExtra("email", mail)
                    val classe = spinner.selectedItem.toString()
                    intent.putExtra("classe", classe)
                    startActivity(intent)
                })
                builder.show()

            } else {
                Toast.makeText(this, "Champs vides", Toast.LENGTH_LONG).show()
                val snack = Snackbar.make(it,"Champs vides",Snackbar.LENGTH_LONG)
                snack.show()
            }
            }
        }
    }

