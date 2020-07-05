package com.example.gdgandroidwebinar6

import java.util.concurrent.atomic.AtomicReference

class Consumable<out T : Any>(value: T?) {
    private val value = AtomicReference(value)

    fun consume(): T? = value.getAndSet(null)

    // Cannot access generic AtomicReference in equals
    private fun get() = value.get()

    override fun equals(other: Any?) = (other as? Consumable<*>)?.get() == get()
    override fun hashCode() = get().hashCode()
    override fun toString() = "Consumable(value=${value.get()})"
}

inline fun <T : Any, R> Consumable<T>.consume(action: (T) -> R): R? {
    val value = consume()
    return if (value != null) action(value) else null
}
