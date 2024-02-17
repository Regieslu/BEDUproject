package com.team4.crm.data

fun ArrayList<Contacto2>.asContacto(): ArrayList<Contacto> {
    val list = arrayListOf<Contacto>()
    this.forEach {
        list.add(Contacto(
            it.id,
            it.name,
            it.phones[0]
        ))
    }
    return list
}
data class Contacto2 (
    var id: String,
    var name: String,
    var phones: ArrayList<String>,
    var emails: ArrayList<String>
) {
    constructor() : this(
       "NO_ID", "NO_NAME", arrayListOf("NO_PHONES"), arrayListOf("NO_EMAILS")
    ) {}
}