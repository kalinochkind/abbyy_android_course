package main

import kotlin.reflect.KProperty

class StorageDelegate<T> constructor(val key: String, value: T) {

    private val storage = Storage()

    private fun putValue(value: T) {
        if (value is String) {
            storage.put(key, value)
        }
        if (value is Int) {
            storage.put(key, value)
        }
        if (value is Long) {
            storage.put(key, value)
        }
        if (value is Char) {
            storage.put(key, value)
        }
        if (value is Double) {
            storage.put(key, value)
        }
    }

    init {
        putValue(value)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T = storage.get(key) as T

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putValue(value)
    }

}