package com.cryptotracker.cryptotracker.data.source.remote
import com.cryptotracker.cryptotracker.data.source.remote.retrofit.ContactApiClient
import com.suyog.contactlistapp.model.Contact
import kotlinx.coroutines.CoroutineDispatcher
import    com.cryptotracker.cryptotracker.util.Result
import com.suyog.contactlistapp.model.ListContacts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactRemoteDataSource(val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) :
    ContactApiServiceInterface {

    override suspend fun getContact(): Result<ListContacts> = withContext(ioDispatcher) {

        return@withContext try{

            val r =  ContactApiClient.retrofitService.getContacts()

            if(r.isSuccessful){
             Result.Success(r.body()!!)
            }else{
                Result.Success(null)
            }

        }catch (exception :Exception){
            Result.Error(exception)
        }
    }

    override suspend fun postContact(contact: Contact): Result<Contact> = withContext(ioDispatcher){
        return@withContext try{

            val r =  ContactApiClient.retrofitService.postContact(contact)

            if(r.isSuccessful){
                Result.Success(r.body()!!)
            }else{
                Result.Success(null)
            }

        }catch (exception :Exception){
            Result.Error(exception)
        }
    }
}
