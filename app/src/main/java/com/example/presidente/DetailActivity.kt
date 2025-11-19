package com.example.presidente

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.presidente.models.President

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val president = intent.getSerializableExtra("president") as President

        val scrollView = ScrollView(this)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 32, 32, 32)
        }

        val photo = ImageView(this).apply {
            setImageResource(president.photoResId)
            layoutParams = LinearLayout.LayoutParams(400, 400)
        }

        val name = TextView(this).apply {
            text = president.name
            textSize = 24f
            setTypeface(null, android.graphics.Typeface.BOLD)
        }

        val term = TextView(this).apply {
            text = president.term
            textSize = 18f
        }

        val bio = TextView(this).apply {
            text = president.bio
            textSize = 16f
        }

        val milestonesTitle = TextView(this).apply {
            text = "Hitos importantes:"
            textSize = 18f
            setTypeface(null, android.graphics.Typeface.BOLD)
            setPadding(0, 24, 0, 8)
        }

        val milestonesLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        president.milestones.forEach {
            val item = TextView(this).apply {
                text = "• $it"
                textSize = 16f
            }
            milestonesLayout.addView(item)
        }

        layout.apply {
            addView(photo)
            addView(name)
            addView(term)
            addView(bio)
            addView(milestonesTitle)
            addView(milestonesLayout)
        }

        scrollView.addView(layout)
        setContentView(scrollView)
    }
}