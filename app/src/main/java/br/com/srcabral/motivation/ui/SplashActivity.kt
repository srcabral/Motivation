package br.com.srcabral.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.srcabral.motivation.R
import br.com.srcabral.motivation.infra.MotivationConstants
import br.com.srcabral.motivation.infra.MotivationConstants.KEY.PERSON_NAME
import br.com.srcabral.motivation.infra.SecurityPreferences

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mSecurityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mSecurityPreferences = SecurityPreferences(this)

        val buttonSalve = findViewById<Button>(R.id.button_salvar)

        supportActionBar?.hide()

        buttonSalve.setOnClickListener(this)

        verifyName()
    }

    private fun verifyName() {
        val name = mSecurityPreferences.getString(PERSON_NAME)
        if (name != ""){
            mSecurityPreferences.storeString(PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onClick(view: View) {
        val id = view.id
        if (id == R.id.button_salvar){
            handleSalve()
        }
    }

    private fun handleSalve() {
        val editName = findViewById<EditText>(R.id.edittext_nome)
        val name = editName.text.toString()

        if (name != ""){
            mSecurityPreferences.storeString(PERSON_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else{
            Toast.makeText(this, "Informe seu nome!", Toast.LENGTH_SHORT).show()
        }
    }
}