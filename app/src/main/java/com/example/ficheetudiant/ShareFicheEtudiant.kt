package com.example.ficheetudiant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShareFicheEtudiant : AppCompatActivity() {
    lateinit var txt : TextView
    lateinit var btn2 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_fiche_etudiant)

        txt = findViewById(R.id.donnee)
        registerForContextMenu(txt)
        val extras = intent.extras
        val val1 = extras!!.getString("nom")
        val val2 = extras!!.getString("dtn")
        val val3 = extras!!.getString("email")
        val val4 = extras!!.getString("classe")


        val msg = "Etudiant : $val1 \n Date de naissance : $val2  \n email : $val3 \n Classe : $val4"
        txt.text = msg

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, msg)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}