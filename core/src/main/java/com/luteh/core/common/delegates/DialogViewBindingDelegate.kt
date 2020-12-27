package com.luteh.core.common.delegates

import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : ViewBinding> DialogFragment.viewBinding() = DialogViewBindingDelegate(T::class.java)

class DialogViewBindingDelegate<T : ViewBinding>(private val bindingClass: Class<T>) :
    ReadOnlyProperty<DialogFragment, T> {
    /**
     * initiate variable for binding view
     */
    private var binding: T? = null

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: DialogFragment, property: KProperty<*>): T {
        binding?.let { return it }

        /**
         * inflate View class
         */
        val inflateMethod = bindingClass.getMethod("inflate", LayoutInflater::class.java)

        /**
         * Bind layout
         */
        val invokeLayout = inflateMethod.invoke(null, LayoutInflater.from(thisRef.context)) as T

        /**
         * Return binding layout
         */
        return invokeLayout.also { this.binding = it }
    }
}