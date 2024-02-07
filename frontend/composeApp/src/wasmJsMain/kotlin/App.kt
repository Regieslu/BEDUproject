import EmpleadoController.Companion.DONE
import EmpleadoController.Companion.NO_CONTENT
import EmpleadoController.Companion.OK
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.w3c.xhr.XMLHttpRequest

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun App() {
    MaterialTheme {

        var URL by remember { mutableStateOf("http://localhost:8081/empleados") }

        var empleadosGET by remember { mutableStateOf("") }
        fun reFetchEmpleadosGET() {
            // TODO es costoso hacer el GET para repopular? si no es costoso, se evita desaparecer elementos que no hayan sido borrados. Lo anterior hasta implementar ViewModel
            val xmlHttp = XMLHttpRequest()
            xmlHttp.open("GET", URL)
            xmlHttp.onload = {
                if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
                    empleadosGET = xmlHttp.responseText
                } else {
                    //TODO display Un problema ha ocurrido ANIMACION
                    empleadosGET = ""
                }
            }
            xmlHttp.send()
        }


        //AlertDialog Init-Welcome
        var showAlertDialogInit by remember { mutableStateOf(true) }
        AnimatedVisibility(showAlertDialogInit) {
            AlertDialog(onDismissRequest = {}, text = {
                Column {
                    Text("Ingresa URL Backend")
                    TextField(
                        value = URL,
                        onValueChange = { URL = it },
                    )
                }
            }, title = { Text("Bienvenido") }, confirmButton = {
                Button(onClick = {
                    showAlertDialogInit = false
                    reFetchEmpleadosGET()
                }) { Text("Iniciar") }
            })
        }


        //AlertNewEntryForm POST_METHOD
        var showNewEntryForm by remember { mutableStateOf(false) }
        var showNewEntryFormAlert by remember { mutableStateOf(false) }
        var messageNewEntryFormAlert by remember { mutableStateOf("") }
        var inputNuevoEmpleadoPropertyNombre by remember { mutableStateOf("") }
        var inputNuevoEmpleadoPropertyApellido by remember { mutableStateOf("") }
        var inputNuevoEmpleadoPropertyEmail by remember { mutableStateOf("ejemplo@correo.com") }
        var inputNuevoEmpleadoPropertyDepartamento by remember { mutableStateOf("") }
        var inputNuevoEmpleadoPropertyPuesto by remember { mutableStateOf("") }
        AnimatedVisibility(showNewEntryFormAlert) {
            AlertDialog(onDismissRequest = {}, text = {
                Column(
                    Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    //FormNuevoEmpleado()
                    OutlinedTextField(value = inputNuevoEmpleadoPropertyNombre,
                        onValueChange = { inputNuevoEmpleadoPropertyNombre = it },
                        label = { Text("Nombre") })
                    OutlinedTextField(value = inputNuevoEmpleadoPropertyApellido,
                        onValueChange = { inputNuevoEmpleadoPropertyApellido = it },
                        label = { Text("Apellido") })
                    OutlinedTextField(value = inputNuevoEmpleadoPropertyEmail,
                        onValueChange = { inputNuevoEmpleadoPropertyEmail = it },
                        label = { Text("Email") })
                    OutlinedTextField(value = inputNuevoEmpleadoPropertyDepartamento,
                        onValueChange = { inputNuevoEmpleadoPropertyDepartamento = it },
                        label = { Text("Departamento") })
                    OutlinedTextField(value = inputNuevoEmpleadoPropertyPuesto,
                        onValueChange = { inputNuevoEmpleadoPropertyPuesto = it },
                        label = { Text("Puesto") })

                    Button(onClick = {
                        val xmlHttp = XMLHttpRequest()
                        xmlHttp.open("POST", URL)
                        xmlHttp.setRequestHeader("Content-type", "application/json")
                        xmlHttp.onload = {
                            if (xmlHttp.readyState == 4.toShort()) {
                                if (xmlHttp.status == 200.toShort()) {
                                    println(xmlHttp.responseText)//callback.invoke(xmlHttp.responseText)
                                    println("PRINTLN from POST action")

                                    //TODO only if CREATED is returned response
                                    //                                inputNuevoEmpleadoPropertyNombre = ""
                                    //                                inputNuevoEmpleadoPropertyApellido = ""
                                    //                                inputNuevoEmpleadoPropertyEmail = ""
                                    //                                inputNuevoEmpleadoPropertyDepartamento = ""
                                    //                                inputNuevoEmpleadoPropertyPuesto = ""

                                    messageNewEntryFormAlert = "200: ${xmlHttp.responseText}"
                                } else if (xmlHttp.status == 201.toShort()) {
                                    messageNewEntryFormAlert = "201: ${xmlHttp.responseText}"
                                    showNewEntryFormAlert = false
                                    reFetchEmpleadosGET()
                                } else {
                                    messageNewEntryFormAlert = "${xmlHttp.status}: ${xmlHttp.responseText}"

                                }
                            } else {
                                messageNewEntryFormAlert = xmlHttp.responseText
                            }
                        }
                        xmlHttp.send(
                            Json.parseToJsonElement(
                                """
                                {
                                    "nombre": "$inputNuevoEmpleadoPropertyNombre",
                                    "apellido": "$inputNuevoEmpleadoPropertyApellido",
                                    "email": "$inputNuevoEmpleadoPropertyEmail",
                                    "departamento": "$inputNuevoEmpleadoPropertyDepartamento",
                                    "puesto": "$inputNuevoEmpleadoPropertyPuesto"
                                }
                            """.trimIndent()
                            ).toString()
                        )

                    }) {
                        Text("AGREGAR")
                    }
                    Text(messageNewEntryFormAlert)
                }
            }, confirmButton = {
                Button(onClick = {
                    showNewEntryFormAlert = false
                    messageNewEntryFormAlert = ""
                    reFetchEmpleadosGET()
                }) {
                    Text("Cancelar")
                }
            })
        }



        //AlertDialog SearchByID
        var rememberSearchById by remember { mutableStateOf("") }
        var labelSearchById by remember { mutableStateOf("") }
        var showAlertDialogSearchById by remember { mutableStateOf(false) }
        AnimatedVisibility(showAlertDialogSearchById) {
            AlertDialog(onDismissRequest = {}, text = {
                Column {
                    Text("Ingresa ID existente")
                    Row {
                        TextField(
                            value = rememberSearchById,
                            onValueChange = { rememberSearchById = it },
                        )
                        Button(
                            onClick = {
                                //GET_METHOD SearchById
                                val xmlHttp = XMLHttpRequest()
                                xmlHttp.open("GET", "$URL/$rememberSearchById")
                                xmlHttp.onload = {
                                    if (xmlHttp.readyState == 4.toShort()) {
                                        if (xmlHttp.status == OK.toShort()) {//OK
                                            labelSearchById = xmlHttp.responseText
                                        } else {
                                            labelSearchById = "${xmlHttp.status}: ${xmlHttp.responseText}"
                                        }
                                    } else {
                                        labelSearchById = "${xmlHttp.status}: ${xmlHttp.responseText}"
                                    }
                                }
                                xmlHttp.send()

                            }, modifier = Modifier.padding(start = 8.dp).align(Alignment.CenterVertically)
                        ) { Text("Buscar") }
                    }
                    Spacer(Modifier.height(20.dp))
                    Text(labelSearchById)
                }
            }, title = { Text("Buscar por ID") }, confirmButton = {
                Button(onClick = {
                    showAlertDialogSearchById = false
                    rememberSearchById = ""
                }) { Text("Cancelar") }
            })
        }

        //Alert Dialog Update By ID
        var rememberUpdateById by remember { mutableStateOf((-1).toLong()) }
        var showAlertDialogUpdateByID by remember { mutableStateOf(false) }
        var labelAlertDialogUpdateByID by remember { mutableStateOf("") }
        //var inputEditEmpleadoBoolean = As true since EmpleadoController requires entire Body to make Changes
        var inputEditEmpleadoPropertyNombre by remember { mutableStateOf("") }
        var inputEditEmpleadoBooleanNombre by remember { mutableStateOf(!false) }
        var inputEditEmpleadoPropertyApellido by remember { mutableStateOf("") }
        var inputEditEmpleadoBooleanApellido by remember { mutableStateOf(!false) }
        var inputEditEmpleadoPropertyEmail by remember { mutableStateOf("ejemplo@correo.com") }
        var inputEditEmpleadoBooleanEmail by remember { mutableStateOf(!false) }
        var inputEditEmpleadoPropertyDepartamento by remember { mutableStateOf("") }
        var inputEditEmpleadoBooleanDepartamento by remember { mutableStateOf(!false) }
        var inputEditEmpleadoPropertyPuesto by remember { mutableStateOf("") }
        var inputEditEmpleadoBooleanPuesto by remember { mutableStateOf(!false) }
        AnimatedVisibility(showAlertDialogUpdateByID) {
            AlertDialog(onDismissRequest = {}, text = {
                Column {
                    OutlinedTextField(value = inputEditEmpleadoPropertyNombre, onValueChange = {
                        inputEditEmpleadoPropertyNombre = it
                        inputEditEmpleadoBooleanNombre = true
                    }, label = { Text("Nombre") })
                    OutlinedTextField(value = inputEditEmpleadoPropertyApellido, onValueChange = {
                        inputEditEmpleadoPropertyApellido = it
                        inputEditEmpleadoBooleanApellido = true
                    }, label = { Text("Apellido") })
                    OutlinedTextField(value = inputEditEmpleadoPropertyEmail, onValueChange = {
                        inputEditEmpleadoPropertyEmail = it
                        inputEditEmpleadoBooleanEmail = true
                    }, label = { Text("Email") })
                    OutlinedTextField(value = inputEditEmpleadoPropertyDepartamento, onValueChange = {
                        inputEditEmpleadoPropertyDepartamento = it
                        inputEditEmpleadoBooleanDepartamento = true
                    }, label = { Text("Departamento") })
                    OutlinedTextField(value = inputEditEmpleadoPropertyPuesto, onValueChange = {
                        inputEditEmpleadoPropertyPuesto = it
                        inputEditEmpleadoBooleanPuesto = true
                    }, label = { Text("Puesto") })

                    Button(onClick = {
                        // Update PUT method
                        val body = Json.parseToJsonElement(
                            ("""{""" + if (inputEditEmpleadoBooleanNombre) {
                                """ "nombre": "$inputEditEmpleadoPropertyNombre" """ + if (inputEditEmpleadoBooleanNombre && (inputEditEmpleadoBooleanApellido || inputEditEmpleadoBooleanEmail || inputEditEmpleadoBooleanDepartamento || inputEditEmpleadoBooleanPuesto)) ","
                                else ""
                            } else {
                                ""
                            } +

                                    if (inputEditEmpleadoBooleanApellido) {
                                        """ "apellido": "$inputEditEmpleadoPropertyApellido" """ + if (inputEditEmpleadoBooleanApellido && (inputEditEmpleadoBooleanEmail || inputEditEmpleadoBooleanDepartamento || inputEditEmpleadoBooleanPuesto)) ","
                                        else ""
                                    } else {
                                        ""
                                    } +

                                    if (inputEditEmpleadoBooleanEmail) {
                                        """ "email": "$inputEditEmpleadoPropertyEmail" """ + if (inputEditEmpleadoBooleanEmail && (inputEditEmpleadoBooleanDepartamento || inputEditEmpleadoBooleanPuesto)) ","
                                        else ""
                                    } else {
                                        ""
                                    } +

                                    if (inputEditEmpleadoBooleanDepartamento) {
                                        """ "departamento": "$inputEditEmpleadoPropertyDepartamento" """ + if (inputEditEmpleadoBooleanDepartamento && (inputEditEmpleadoBooleanPuesto)) ","
                                        else ""
                                    } else {
                                        ""
                                    } +

                                    if (inputEditEmpleadoBooleanPuesto) {
                                        """ "puesto": "$inputEditEmpleadoPropertyPuesto" """
                                    } else {
                                        ""
                                    } + """  }
                    """).trimIndent()
                        ).toString()

                        val id = rememberUpdateById
                        val xmlHttp = XMLHttpRequest()
                        xmlHttp.open("PUT", "$URL/$id")
                        xmlHttp.setRequestHeader("Content-type", "application/json")
                        println("xmlHttp.open(PUT/$id, URL)")
                        xmlHttp.onload = {
                            if (xmlHttp.readyState == DONE) {
                                if (xmlHttp.status == 204.toShort())//NoContent or PUT done
                                {
                                    println(xmlHttp.responseText)
                                    labelAlertDialogUpdateByID = "${xmlHttp.status}: ${xmlHttp.responseText}"
                                    showAlertDialogUpdateByID =
                                        false//TODO set to TRUE and disable edition since already updated
                                    reFetchEmpleadosGET()
                                    rememberUpdateById = -1
                                    labelAlertDialogUpdateByID =
                                        ""//TODO move label Alert after reFetch and inside reFetch
                                } else if (xmlHttp.status == 200.toShort()) {//OK
                                    labelAlertDialogUpdateByID = "${xmlHttp.status}: ${xmlHttp.responseText}"
                                } else {
                                    labelAlertDialogUpdateByID = "${xmlHttp.status}: ${xmlHttp.responseText}"
                                }
                            } else {
                                println(xmlHttp.responseText)
                                labelAlertDialogUpdateByID = "${xmlHttp.status}: ${xmlHttp.responseText}"
                            }
                        }
                        xmlHttp.send(body)
                        println("xmlHttp.send($body)")


                    }) { Text("Actualizar") }
                    Text(labelAlertDialogUpdateByID)

                }
            }, title = { Text("Actualizando ID $rememberUpdateById") }, confirmButton = {
                Button(
                    onClick = {
                        showAlertDialogUpdateByID = false
                        rememberUpdateById = -1
                        labelAlertDialogUpdateByID = ""
                    },
                ) { Text("Cancelar") }
            })
        }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            //TopAppBar
            TopAppBar(elevation = 4.dp, title = { Text("TEAM 4 API") }, actions = {
                IconButton(onClick = { showAlertDialogInit = true }) {
                    Icon(Icons.Filled.Home, contentDescription = "Inicio")
                }
//                IconButton(onClick = { showAlertDialogInit = true }) {
//                    Icon(Icons.Filled.Settings, contentDescription = "Cambiar URL backend")
//                }
            })

            //TABLE
            if (empleadosGET == "") {
                Text("Algo salio mal")
            }
            if (empleadosGET == "") return@Column

            val iconWeights = .15f
            val iconButtonModifierBase = Modifier.weight(iconWeights / 2f)
            val iconButtonModifierBody = iconButtonModifierBase.background(Color.White)
            val iconButtonModifierHeader = iconButtonModifierBase.background(Color.DarkGray).height(40.dp)
            val headers = arrayOf("Nombre", "Apellido", "Email", "Puesto", "Departamento")
            //TableHeaders
            Row {
                headers.forEach {
                    Text(
                        textAlign = TextAlign.Start,
                        text = it, overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        modifier = Modifier.weight(((1f - iconWeights) / headers.size.toFloat()))
                            .background(Color.DarkGray, RoundedCornerShape(0.dp)).height(40.dp).wrapContentHeight()
                            .padding(start = 4.dp),
                    )
                }
                IconButton(onClick = {
                    //POST_METHOD
                    showNewEntryFormAlert = true
                }, modifier = iconButtonModifierHeader) {
                    Icon(Icons.Filled.Add, contentDescription = "Agregar nuevo", tint = Color.White)
                }
                IconButton(onClick = {
                    //GET_METHOD searchById
                    showAlertDialogSearchById = true
                }, modifier = iconButtonModifierHeader) {
                    Icon(Icons.Filled.Search, contentDescription = "Buscar por ID", tint = Color.White)
                }
            }
            //Table Cells
            @Composable
            fun TableItemTextField(text: String) {
                var cellText by remember { mutableStateOf(text) }
                TextField(
                    value = cellText,
                    onValueChange = { cellText = it },
                    readOnly = true,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth(((1f - iconWeights) / headers.size.toFloat()))
                        .padding(start = 4.dp, top = 4.dp).background(Color.LightGray, RoundedCornerShape(4.dp)),
                    singleLine = false,
                    maxLines = 1
                )
            }
            // Table
            val list = Json.parseToJsonElement(empleadosGET).jsonArray
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(items = list, itemContent = { item ->
                    FlowRow {
                        val id: Long = (item.jsonObject["id"].toString().replace("\"", "")).toLong()
                        val nombre = item.jsonObject["nombre"].toString().replace("\"", "")
                        val apellido = item.jsonObject["apellido"].toString().replace("\"", "")
                        val email = item.jsonObject["email"].toString().replace("\"", "")
                        val puesto = item.jsonObject["puesto"].toString().replace("\"", "")
                        val departamento = item.jsonObject["departamento"].toString().replace("\"", "")

                        TableItemTextField(nombre)
                        TableItemTextField(apellido)
                        TableItemTextField(email)
                        TableItemTextField(puesto)
                        TableItemTextField(departamento)
                        IconButton(onClick = {
                            // PUT Method
                            //EmpleadoController.update
                            inputEditEmpleadoPropertyNombre = nombre
                            inputEditEmpleadoPropertyApellido = apellido
                            inputEditEmpleadoPropertyEmail = email
                            inputEditEmpleadoPropertyPuesto = puesto
                            inputEditEmpleadoPropertyDepartamento = departamento
                            showAlertDialogUpdateByID = true
                            rememberUpdateById = id

                        }, modifier = iconButtonModifierBody) {
                            Icon(Icons.Filled.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = {

                            // DELETE Method
                            // EmpleadoController.deleteById()
                            val xmlHttp = XMLHttpRequest()
                            xmlHttp.open("DELETE", "$URL/$id")
                            println("xmlHttp.open(DELETE/$id, URL)")
                            xmlHttp.onload = {
                                if (xmlHttp.readyState == DONE && xmlHttp.status == NO_CONTENT) {
                                    println(xmlHttp.responseText)
                                    reFetchEmpleadosGET()
                                } else {
                                    println(xmlHttp.responseText)
                                }
                            }
                            xmlHttp.send()
                            println("xmlHttp.send()")

                        }, modifier = iconButtonModifierBody) {
                            Icon(
                                Icons.Filled.Delete, contentDescription = "Eliminar"
                            )

                        }
                    }
                })
            }

//
//            Animated
//
//            if (reFetchEmpleadosGET() != "") {
//                TableComposable()
//            } else {
//                Text("Hubo un problema al cargar")
//                return@Column
//                Button(onClick = {
//                    val url = "http://localhost:8081/empleados"
//                    val xmlHttp = XMLHttpRequest()
//                    xmlHttp.open("GET", url)
//                    xmlHttp.onload = {
//                        if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
//                            //callback.invoke(xmlHttp.responseText)
//                            println(xmlHttp.responseText)
//                            empleadosGET = xmlHttp.responseText
//                            showTable = true
//                        }
//                    }
//                    xmlHttp.send()
//
//                }) { Text("Mostrar todo") }
//            }
//            AnimatedVisibility(showTable) {
//                TableComposable()
//            }
        }
    }
}

class EmpleadoController {
    companion object {
        //ReadyState
        const val DONE: Short = 4

        //Status
        const val OK: Short = 200
        const val NO_CONTENT: Short = 204

    }

}