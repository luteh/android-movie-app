package com.luteh.core.common.delegates

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * Created by Luthfan Maftuh
 * Email : luthfanmaftuh@gmail.com
 */
class TrimDelegate : ReadWriteProperty<Any?, String> {

    private var trimmedValue: String = ""

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        // Return the trimmed value
        return trimmedValue
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        // Trims value
        trimmedValue = value.trim()
    }
}