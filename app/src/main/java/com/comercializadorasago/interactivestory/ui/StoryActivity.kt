package com.comercializadorasago.interactivestory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import java.util.*
import com.comercializadorasago.interactivestory.R
import com.comercializadorasago.interactivestory.model.Page
import com.comercializadorasago.interactivestory.model.Story

class StoryActivity : AppCompatActivity() {

    private var story: Story = Story()
    private lateinit var storyImageView: ImageView
    lateinit var storyTextView: TextView
    lateinit var choice1Button: Button
    lateinit var choice2Button: Button
    lateinit var name: String
    var pageStack: Stack<Int> = Stack<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        storyImageView = findViewById(R.id.storyImageView)
        storyTextView = findViewById(R.id.storyTextView)
        choice1Button = findViewById(R.id.choice1Button)
        choice2Button = findViewById(R.id.choice2Button)

        name = intent?.getStringExtra(getString(R.string.key_name)).toString()

        loadPage(0)

    }

    private fun loadPage(pageNumber: Int) {

        pageStack.push(pageNumber)
        val i:Int=0
        val page: Page? = story.getPage(pageNumber)

        if (page != null) {

            val image = ContextCompat.getDrawable(this, page.imageID)

            storyImageView.setImageDrawable(image)

            var pageText:String = getString(page.textID)
            pageText = String.format(pageText, name)
            storyTextView.text = pageText

            if (page.isFinalPage){
                choice1Button.isInvisible = true
                choice2Button.text = getText(R.string.endgame)
                choice2Button.setOnClickListener{
                    loadPage(0)
                }
            }
            else {

                loadButtons(page, i)
            }
        }


    }

    private fun loadButtons(page: Page, i: Int) {
        choice1Button.isInvisible = false
        choice2Button.isInvisible = false
        var choiceInt = i

        page.choice[0]?.textID?.let { choice1Button.setText(it) }
        choice1Button.setOnClickListener {
            choiceInt = page.choice[0]?.nextPage!!
            loadPage(choiceInt)
        }

        page.choice[1]?.textID?.let { choice2Button.setText(it) }
        choice2Button.setOnClickListener {
            choiceInt = page.choice[1]?.nextPage!!
            loadPage(choiceInt)
        }
    }


    override fun onBackPressed() {
        pageStack.pop()
        if (pageStack.empty()) {
            super.onBackPressed()
        }
        else{loadPage(pageStack.pop())}
    }
}
