@file:OptIn(ExperimentalLayoutApi::class, ExperimentalFoundationApi::class)

package com.team4.crm

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team4.crm.data.Contacto
import com.team4.crm.data.Contacto2
import com.team4.crm.data.asContacto
import com.team4.crm.ui.theme.CRMobileTheme

const val IS_RELEASE = true

class MainActivity : ComponentActivity() {

    private lateinit var contactsList: ArrayList<Contacto2>
    private fun getPermissions() {
        (registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(applicationContext, "Permissions granted $it", Toast.LENGTH_SHORT)

        }).launch(android.Manifest.permission.READ_CONTACTS)
    }

    var checkedIDS = arrayListOf<Long>()
    fun actionEnviarCorreo() {
        val idsEmails = arrayListOf<String>()
        checkedIDS.forEach {
            var contact: Contacto2? =
                contactsList.find { contacto2 -> contacto2.id.toInt() == it.toInt() }
            println("ID:$it\t" + contact)
            if (contact != null) {
                if (contact.emails.isNotEmpty()) {
                    idsEmails.add(contact.emails[0])
                }
            }
        }

        if (idsEmails.isEmpty()) {
            Toast.makeText(
                applicationContext,
                "No email seleccionados",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val emailAddresses = idsEmails//listOf("address1@example.com", "address2@example.com")
        val addresses = emailAddresses.toTypedArray()
        val subject = "Al equipo 4"
        val fileUris = listOf() ?: listOf(
            Uri.parse("file://path/to/file1.jpg"),
            Uri.parse("file://path/to/file2.pdf")
        )
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, "Saludos tengan todos y excelente inicio de semana") // Replace with your content
            if (fileUris.isNotEmpty()) {
                // Attach files (Optional)
                val uris = fileUris.toTypedArray()
                action = Intent.ACTION_SEND_MULTIPLE
                putExtra(Intent.EXTRA_STREAM, uris)
                type = "text/plain"
            }
        }
        startActivity(
            Intent.createChooser(
                emailIntent, "Elige una aplicacion"
            )
        )

    }

    fun actionEnviarMensaje() {
        startActivity(
            Intent.createChooser(
                Intent().apply {
                    action = Intent.ACTION_SENDTO
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "This is my text to send"
                    )//TODO crear plantillas button
                    type = "text/plain"
                }, null
            )
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getPermissions()
        contactsList = getContacts2()
        setContent {
            CRMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(title = { Text("Equipo 4 CRMobile", maxLines = 1) })

                        Row(
                            Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            val buttonModifier = Modifier.padding(bottom=5.dp, start = 5.dp, end=5.dp)
                            Button(modifier = buttonModifier, onClick = { actionEnviarCorreo() }) { Text(text = "Enviar correo")}
                            Button(modifier = buttonModifier, onClick = { TODO() }) { Text(text = "Crear plantilla") }
                        }
                        ContactsTable(contactsList)
                    }
                }


            }
        }
    }

    @SuppressLint("Range")
    private fun getContacts2(): ArrayList<Contacto2> {
        println("getContacts2()")

        val contactsList = arrayListOf<Contacto2>()

        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null
        )
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val actualContacto = Contacto2()

                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                println("\n\nID\t$id")
                actualContacto.id = id
                val name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        ?: "NULL_CURSOR"
                println("NAME\t$name")
                actualContacto.name = name
                if (true || cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        .toInt() > 0
                ) {
                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    val phones = arrayListOf<String>()
                    while (phoneCursor!!.moveToNext()) {
                        val phoneNumber = phoneCursor.getString(
                            phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        )
                        println("PHONE_NUMBER\t$phoneNumber")
                        phones.add(phoneNumber)
                    }
                    actualContacto.phones = phones
                    phoneCursor.close()

                    val emailCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        arrayOf(id), null
                    )
                    val emails = arrayListOf<String>()
                    while (emailCursor!!.moveToNext()) {
                        val email =
                            emailCursor.getString(emailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        println("EMAIL\t$email")
                        emails.add(email)
                    }
                    actualContacto.emails = emails
                    emailCursor.close()
                }
                contactsList.add(actualContacto)
            }
            cursor.close()
            contactsList.sortBy { l -> l.name }
            return contactsList
        }
        return throw RuntimeException("No se encontro contactos")
    }

    @SuppressLint("Range")
    private fun getContacts(): ArrayList<Contacto> {
        return getContacts2().asContacto()

        val contactsList: ArrayList<Contacto> = arrayListOf()
        val contentResolver: ContentResolver = contentResolver
        val projection = arrayOf(
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,
        )
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            projection,
            ContactsContract.Contacts.HAS_PHONE_NUMBER + "> 0 AND LENGTH(" + ContactsContract.CommonDataKinds.Phone.NUMBER + ")>0",
            null,
            "display_name ASC"
        )
        if (cursor != null && cursor.count > 0) {
            while (cursor.moveToNext()) {
                val id: String = try {
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID))
                } catch (e: Exception) {
                    666666666.toString()
                }

                val person =
                    ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, id.toLong())
                var name =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                var mobileNumber: String =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                var email: String = "__EMAIL__"

                try {
                    var emailCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS),
                        ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        arrayOf(id),
                        null
                    )
                    while (emailCursor!!.moveToNext()) {
                        var email_ = emailCursor.getString(0)
                        println("email_\t $email_")
                    }
                    emailCursor!!.move(id.toInt())
                    email = emailCursor?.getString(0) ?: "NULL"
                    emailCursor?.close()
                } catch (e: Exception) {
                    email = "CATCH $e"
                }

                println("CONTACT $id" + "\nPerson\t$person\nName\t$name\nMobileNumber\t$mobileNumber\nEmail:$email\n\n\n")
                contactsList.add(
                    Contacto(
                        id, name, mobileNumber
                    )
                )
            }
            cursor.close()
        }
        return contactsList
    }

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    fun ContactsTable(contactsList: ArrayList<Contacto2>) {
        val contactsListGrouped = contactsList.groupBy { it.name[0] }
        LazyColumn {
            contactsListGrouped.forEach { (initial, contactsForInitial) ->
                stickyHeader {
                    Divider()
                    Box(
                        Modifier
                            .background(Color.hsv(0f, 0f, .88f, 1f))
                            .fillMaxWidth()
                    ) {
                        Text(
                            color = Color.hsv(0f, 0f, .55f, 1f),
                            text = initial.toString(),
                            modifier = Modifier
                                .padding(start = 17.dp)
                        )
                    }
                    //Divider()
                }
                items(contactsForInitial) { contact ->
                    var checked by remember { mutableStateOf(false) }
                    Column(
                        Modifier
                            .fillParentMaxWidth()
                            .padding(bottom = 10.dp)
                    ) {
                        Divider()
                        LazyRow(verticalAlignment = Alignment.CenterVertically) {
                            item {
                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = { isChecked ->
                                        checked = isChecked
                                        if (isChecked) checkedIDS.add(contact.id.toLong())
                                        else checkedIDS.remove(contact.id.toLong())
                                    }
                                )
                            }
                            item {
                                Text(
                                    text = (if (!IS_RELEASE) contact.id + " : " else "") + contact.name,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                        Divider(Modifier.padding(start = 49.dp))
                        val dataTextStyle = Modifier.padding(start = 55.dp, top = 5.dp)

                        if (!contact.phones.isEmpty()) Text(
                            text = contact.phones[0],
                            modifier = dataTextStyle
                        )
                        if (!contact.emails.isEmpty()) Text(
                            text = contact.emails[0],
                            modifier = dataTextStyle
                        )
                    }
                }
            }
            return@LazyColumn
            //Body
            items(contactsList, key = { contactList -> contactList.name[0] }) { item ->
                Text(item.name)
            }
            return@LazyColumn
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CRMobileTheme {
        Greeting("Android")
    }
}