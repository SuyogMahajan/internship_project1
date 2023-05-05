package com.suyog.contactlistapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.cryptotracker.cryptotracker.data.source.remote.ContactRemoteDataSource
import com.suyog.contactlistapp.model.Contact
import    com.cryptotracker.cryptotracker.util.Result
import com.suyog.contactlistapp.model.ListContacts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application){

    private val _contactList = MutableLiveData<Result<ListContacts>>()
    val contactList: LiveData<Result<ListContacts>> = _contactList

    private val remoteDataSource = ContactRemoteDataSource()

    fun getContacts() {
        CoroutineScope(Dispatchers.Main).launch {
            val result = remoteDataSource.getContact()
            _contactList.value = result
        }
    }

    fun postContact(contact: Contact) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val result = remoteDataSource.postContact(contact)
                Log.d("DATA_CAME",result.toString())
            }catch (e:Exception) {
                Log.d("DATA_CAME", e.message.toString())
            }
        }
    }

}
