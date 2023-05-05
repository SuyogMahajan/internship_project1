package com.suyog.contactlistapp.model

data class Contact (

     var id       : Int?    = null,
     var name     : String? = null,
     var mobile   : String? = null,
     var category : String? = null,
     var company : String? = null

){
     fun toJson(): Map<String, Any?> {
          val json = mutableMapOf<String, Any?>()
          json["id"] = id
          json["name"] = name
          json["mobile"] = mobile
          json["category"] = category
          json["company"] = company
          return json
     }
}

data class ListContacts(
     var contacts: List<Contact>? = null
)