package net.dean.jraw.databind

import com.squareup.moshi.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class EnvelopedListAdapterFactory : JsonAdapter.Factory {
    override fun create(type: Type, annotations: MutableSet<out Annotation>, moshi: Moshi): JsonAdapter<*>? {
        val rawType = Types.getRawType(type)
        if (rawType.name != NAME || type !is ParameterizedType) {
            return null
        }

        val subtype = type.actualTypeArguments[0]

        // Ensure we have the @Enveloped annotation
        Types.nextAnnotations(annotations, Enveloped::class.java) ?: return null
        val delegate = moshi.adapter<Any>(subtype, Enveloped::class.java)

        return EnvelopedListAdapter(delegate)
    }

    companion object {
        const val NAME = "java.util.List"
    }
}

private class EnvelopedListAdapter(private val delegate: JsonAdapter<Any>) : JsonAdapter<List<*>>() {
    override fun toJson(writer: JsonWriter?, value: List<*>?) {
        TODO("not implemented")
    }

    override fun fromJson(reader: JsonReader): List<*>? {
        val list: MutableList<Any?> = ArrayList()
        reader.beginArray()
        while (reader.hasNext())
            list.add(delegate.fromJson(reader))
        reader.endArray()
        return list
    }
}
