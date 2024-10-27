package utlis

import com.google.gson.Gson

class ReadFile {


    val gson = Gson()

    fun readJsonFileAsString(filePath: String): String? {
        return this.javaClass.classLoader.getResourceAsStream(filePath)?.bufferedReader()?.readText()
    }
    inline fun <reified T>strToEntity(json: String?): T {
        return gson.fromJson(json, T::class.java)
    }

    inline fun <reified T >readEntity(filePath: String) : T{
        val jsonStr = readJsonFileAsString(filePath)
        return strToEntity<T>(jsonStr)
    }
}