package wasmMain

import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.setValue
import org.w3c.xhr.XMLHttpRequest

/**
 * [MDN Web APIs](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest/readyState)*/
enum class XMLHttpReadyState {
    /*0*/    UNSENT,//	Client has been created. open() not called yet.
    /*1*/    OPENED,//	open() has been called.
    /*2*/    HEADERS_RECEIVED,//	send() has been called, and headers and status are available.
    /*3*/    LOADING,//	Downloading; responseText holds partial data.
    /*4*/    DONE,//	The operation is complete.
}

class XMLHttpResponseStatus {
    companion object {
        const val OK: Short = 200
    }
}
