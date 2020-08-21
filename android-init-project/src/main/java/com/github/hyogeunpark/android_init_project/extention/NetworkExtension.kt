package com.github.hyogeunpark.android_init_project.extention

import com.google.gson.*
import com.google.gson.reflect.TypeToken

/**
 * @return 문자열로 된 json을 jsonObject로 변환
 */
fun String.toJsonObject(): JsonObject? {
    return try {
        JsonParser().parse(this).asJsonObject
    } catch (ignored: Exception) {
        null
    }
}

/**
 * @param field : JsonObject에서 가져올 field name
 * @return 해당 field name의 JsonArray
 */
fun JsonObject.toFieldJsonArray(field: String): JsonArray? {
    return try {
        if (this.has(field) && this.get(field).isJsonArray) this.getAsJsonArray(field) else null
    } catch (ignored: Exception) {
        null
    }
}

/**
 * @param field : JsonObject에서 가져올 field name
 * @return 해당 field name의 JsonObject
 */
fun JsonObject.toFieldJsonObject(field: String): JsonObject? {
    return try {
        if (this.has(field) && this.get(field).isJsonObject) this.getAsJsonObject(field) else null
    } catch (ignored: Exception) {
        null
    }
}

/**
 * @return String to Model (gson.fromJson() 사용)
 */
inline fun <reified T> String.fromJson(): T {
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    return gson.fromJson(this, object: TypeToken<T>() {}.type)
}

/**
 * @return JsonElement to Model (gson.fromJson() 사용)
 */
inline fun <reified T> JsonElement.fromJson(): T {
    val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
    return gson.fromJson(this, object: TypeToken<T>() {}.type)
}