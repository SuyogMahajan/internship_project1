package com.cryptotracker.cryptotracker.data.source.remote.retrofit

import com.suyog.contactlistapp.model.Contact
import com.suyog.contactlistapp.model.ListContacts
import retrofit2.Response
import retrofit2.http.*

interface ContactApiService {

    @GET("getContacts")
    suspend fun getContacts(): Response<ListContacts>

    @POST("contacts")
    suspend fun postContact(@Body contact: Contact): Response<Contact>

    @GET("delete")
    suspend fun deleteContact(@Query("id") id:Integer)

}
