package com.cryptotracker.cryptotracker.data.source.remote

import    com.cryptotracker.cryptotracker.util.Result
import com.suyog.contactlistapp.model.Contact
import com.suyog.contactlistapp.model.ListContacts

interface ContactApiServiceInterface {
    suspend fun getContact():Result<ListContacts>
    suspend fun postContact(contact: Contact):Result<Contact>

}
