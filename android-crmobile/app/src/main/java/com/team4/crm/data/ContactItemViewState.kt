package com.team4.crm.data

import android.net.Uri

class ContactItemViewState(
    var header: String,
    var id: String?,
    var name: String?,
    var mobileNumber: String?,
    var title: CharSequence?,
    var person: Uri?
) {

}
data class Contacto(
    val id: String,
    val name: String,
    val mobileNumber: String
)
