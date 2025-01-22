import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.n3shemmy3.coffre.domain.currency.Currency

fun ReadCurrencyData(context: Context): List<Currency> {
    val jsonString = context.assets.open("currencies.Json")
        .bufferedReader()
        .use { it.readText() }

    val type = object : TypeToken<List<Currency>>() {}.type
    return Gson().fromJson(jsonString, type)
}
