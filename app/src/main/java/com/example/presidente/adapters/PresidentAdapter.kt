package com.example.presidente.adapters

import android.content.Context
import android.graphics.Typeface
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.presidente.models.President


class PresidentAdapter(
    private val context: Context,
    private val presidents: List<President>,
    private val onClick: (President) -> Unit
) : RecyclerView.Adapter<PresidentAdapter.ViewHolder>() {

    inner class ViewHolder(layout: LinearLayout) : RecyclerView.ViewHolder(layout) {
        val photo: ImageView = ImageView(context)
        val name: TextView = TextView(context)
        val term: TextView = TextView(context)
        init {
            layout.orientation = LinearLayout.HORIZONTAL
            layout.setPadding(24, 24, 24, 24)
            layout.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            photo.layoutParams = LinearLayout.LayoutParams(150, 150)
            layout.addView(photo)
            val textContainer = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(24, 0, 0, 0)
            }
            name.textSize = 18f
            name.setTypeface(null, Typeface.BOLD)
            term.textSize = 14f
            textContainer.addView(name)
            textContainer.addView(term)
            layout.addView(textContainer)
            layout.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onClick(presidents[position])
                }
            }
        }

        fun bind(president: President) {
            photo.setImageResource(president.photoResId)
            name.text = president.name
            term.text = president.term
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LinearLayout(context)
        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(presidents[position])
    }

    override fun getItemCount(): Int = presidents.size
}