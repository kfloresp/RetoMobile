package com.rgk.retomobile.utils
import java.text.SimpleDateFormat
import java.util.*

object StringUtil {
    fun toInt(str: String, defValue: Int): Int {
        try {
            return str.toInt()
        } catch (e: Exception) {
        }
        return defValue
    }

    fun toInt(obj: Any?): Int {
        return if (obj == null) 0 else toInt(obj.toString(), 0)
    }

    fun toLong(obj: String): Long {
        try {
            return obj.toLong()
        } catch (e: Exception) {
        }
        return 0
    }

    fun toDouble(obj: String): Double {
        try {
            return obj.toDouble()
        } catch (e: Exception) {
        }
        return 0.0
    }
  fun toFormat2(value:String): String {
        try {
            return String.format("%.2f", value)
        } catch (e: Exception) {
        }
        return "0"
    }
    fun toFormat3(value:String): String {
        try {
            return String.format("%.3f", value)
        } catch (e: Exception) {
        }
        return "0"
    }

    fun toDouble(obj: String, defValue: Int): Double {
        try {
            return obj.toDouble()
        } catch (e: Exception) {
        }
        return defValue.toDouble()
    }

    fun toBool(b: String): Boolean {
        try {
            return java.lang.Boolean.parseBoolean(b)
        } catch (e: Exception) {
        }
        return false
    }

    fun isInt(`val`: String): Boolean {
        return try {
            `val`.toInt()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isDouble(`val`: String): Boolean {
        return try {
            `val`.toDouble()
            true
        } catch (e: Exception) {
            false
        }
    }

    val timeString: String
        get() {
            val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
            val curDate = Date(System.currentTimeMillis())
            return formatter.format(curDate)
        }
}