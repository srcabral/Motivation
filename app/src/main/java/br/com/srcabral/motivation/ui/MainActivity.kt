package br.com.srcabral.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import br.com.srcabral.motivation.R
import br.com.srcabral.motivation.infra.MotivationConstants
import br.com.srcabral.motivation.infra.MotivationConstants.KEY.PERSON_NAME
import br.com.srcabral.motivation.infra.SecurityPreferences
import br.com.srcabral.motivation.repository.Phrase

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mSecurityPreferences: SecurityPreferences
    private var mPhraseFilter: Int = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val textName = findViewById<TextView>(R.id.textview_name)

        mSecurityPreferences = SecurityPreferences(this)
        textName.text = mSecurityPreferences.getString(PERSON_NAME)

        findViewById<ImageView>(R.id.imageview_all).setColorFilter(resources.getColor(R.color.colorAccent))
        handleNewPhrase()

        findViewById<Button>(R.id.button_new_phrase).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageview_all).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageview_happy).setOnClickListener(this)
        findViewById<ImageView>(R.id.imageview_morning).setOnClickListener(this)

    }

    override fun onClick(view: View) {
        val listFilter = listOf(R.id.imageview_all, R.id.imageview_happy, R.id.imageview_morning)
        val id = view.id

        if (id == R.id.button_new_phrase) {
            handleNewPhrase()
        } else if (id in listFilter) {
            handleFilter(id)
        }
    }

    private fun handleFilter(id: Int) {
        findViewById<ImageView>(R.id.imageview_all).setColorFilter(resources.getColor(R.color.white))
        findViewById<ImageView>(R.id.imageview_happy).setColorFilter(resources.getColor(R.color.white))
        findViewById<ImageView>(R.id.imageview_morning).setColorFilter(resources.getColor(R.color.white))

        when (id) {
            R.id.imageview_all -> {
                findViewById<ImageView>(R.id.imageview_all).setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.imageview_happy -> {
                findViewById<ImageView>(R.id.imageview_happy).setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.HAPPY
            }
            R.id.imageview_morning -> {
                findViewById<ImageView>(R.id.imageview_morning).setColorFilter(resources.getColor(R.color.colorAccent))
                mPhraseFilter = MotivationConstants.PHRASEFILTER.MORNING
            }
        }
    }

    private fun handleNewPhrase() {
        findViewById<TextView>(R.id.textview_phrase).text = Phrase.Mock().getPhrase(mPhraseFilter)
    }
}