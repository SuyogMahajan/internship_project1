package com.suyog.contactlistapp

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.R
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.suyog.contactlistapp.databinding.ActivityAddContactBinding
import com.suyog.contactlistapp.model.Contact

class AddContactActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAddContactBinding
    lateinit var viewModel: ContactViewModel
    val categoryList = listOf("Business" ,"Personal")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)

        val contactName = binding.ContactName.text
        val countryCode = binding.CodePicker.selectedCountryCode
        val mobileNumber = binding.MobileNumber.text
        var category = 0
        val companyName = binding.CompanyName.text

        setSpinnerOptions()
        val spinnerListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if(position == 0)
                   binding.CompanyNameTextBox.visibility = View.VISIBLE
               else
                   binding.CompanyNameTextBox.visibility = View.GONE

                category = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do something when nothing is selected
            }
        }

        binding.CategorySpinner.onItemSelectedListener = spinnerListener


        binding.ContactBtnSave.setOnClickListener({

            val c =Contact(
                id = 100,
                name = contactName.toString(),
                mobile = countryCode.toString() + mobileNumber.toString(),
                category = categoryList[category],
                company = companyName.toString()
            )

            Log.d("DATA_CAME",c.toString())
                   try {
                       viewModel.postContact(
                           c
                       )
                       // Code that might throw an exception
                   } catch (exception: Exception) {
                       Log.d("DATA_CAME",exception.message.toString())
                       // Code to handle the exception
                   }
        })

    }
    private fun setSpinnerOptions() {
        var categoryAdapter = ArrayAdapter(this,
            R.layout.support_simple_spinner_dropdown_item,categoryList)
        binding.CategorySpinner.adapter = categoryAdapter
    }



}