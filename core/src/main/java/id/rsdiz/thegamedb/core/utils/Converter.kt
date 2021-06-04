package id.rsdiz.thegamedb.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(pattern: String): Date? = SimpleDateFormat(pattern, Locale.getDefault()).parse(this)

fun Date.format(newPattern: String): String = SimpleDateFormat(newPattern, Locale.getDefault()).format(this)

object FormatPattern {
    const val ISO_DATE = "yyyy-MM-dd"
    const val LOCAL_DATE = "dd MMMM yyyy"
}
