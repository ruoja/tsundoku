import khttp.get
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject

const val REST_KEY="YOUR_API_KEY"
fun main() {
    print("ISBN: ")
    val isbn = readLine()?.toLong()
    val book = getBookAsJsonObject(isbn)
    val obj = Json{ ignoreUnknownKeys = true; isLenient = true }.decodeFromString<Book>(book)
    //TODO fix serialization
    //println(obj.title)
}

private fun getBookAsJsonObject(isbn: Long?): JSONObject {
    val r = get("https://api2.isbndb.com/book/${isbn}", headers = mapOf(
        "Authorization" to REST_KEY
    )
    )
    return r.jsonObject
}