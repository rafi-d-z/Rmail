package com.example.rmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rmail.EmailFetcher.Companion.getNext5Emails

lateinit var emails: List<Email>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Lookup the RecyclerView in activity layout
        val emailsRv = findViewById<RecyclerView>(R.id.emailsRv)

        // Fetch the list of emails
        emails = EmailFetcher.getEmails()

        // Create adapter passing in the list of emails
        val adapter = EmailAdapter(emails)

        // Attach the adapter to the RecyclerView to populate items
        emailsRv.adapter = adapter

        // Set layout manager to position the items
        emailsRv.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.loadMoreBtn).setOnClickListener {
            // Fetch next 5 emails and display in RecyclerView
            val next5 = EmailFetcher.getNext5Emails()
            (emails as MutableList<Email>).addAll(next5)
            adapter.notifyDataSetChanged()
        }
    }
}

class Email(
    val sender: String,
    val title: String,
    val summary: String){

}
