package com.suyog.contactlistapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.R
import    com.cryptotracker.cryptotracker.util.Result
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cryptotracker.cryptotracker.ui.adapters.ContactsAdapter
import com.suyog.contactlistapp.databinding.ActivityMainBinding
import com.suyog.contactlistapp.model.Contact

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: ContactViewModel
    lateinit var contactsAdapter: ContactsAdapter
    val filterList = listOf("Personal","Buisness","All")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)



        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        viewModel.getContacts()
        contactsAdapter = ContactsAdapter(this)
        contactsAdapter.setOnItemClickInterface(
            object :ContactsAdapter.onItemClickInterface{
                override fun onItemClick(contact: Contact) {
                    Log.d("DATA_CAME",contact.id.toString())
                }
            }
        )
        setSpinnerOptions()
binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.contactsRecyclerView.adapter = contactsAdapter

        viewModel.contactList.observe(
            this, { result -> when(result) {
                is Result.Loading -> {
                    // Show loading indicator
                   Log.d("DATA_COME","loading")
                }
                is Result.Success -> {
                    val contacts = result.data

                    print(contacts.toString())
                    contactsAdapter.updateList(contacts!!.contacts!!)

                    Log.d("DATA_CAME",contacts.toString())
                    // Update UI with list of contacts
                }
                is Result.Error -> {
                    val exception = result.exception
                    // Show error message
                    Log.d("DATA_CAME",exception.message.toString())
                }
            }
            }
        )

        binding.FloatingAction.setOnClickListener(
             {
                 val i = Intent(this, AddContactActivity::class.java)
                 startActivity(i)
            }
        )

    }

    private fun setSpinnerOptions() {
        var categoryAdapter = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item,filterList)
        binding.FilterSpinner.adapter = categoryAdapter
    }

}