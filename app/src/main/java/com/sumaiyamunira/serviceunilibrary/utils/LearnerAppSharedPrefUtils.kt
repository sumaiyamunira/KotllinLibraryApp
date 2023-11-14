package com.sumaiyamunira.serviceunilibrary.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sumaiyamunira.serviceunilibrary.ServiceUniLibraryApp.Companion.getAppInstance
import com.sumaiyamunira.serviceunilibrary.R
import com.sumaiyamunira.serviceunilibrary.models.Room

class LearnerAppSharedPrefUtils {
    private val mSharedPreferences: SharedPreferences = getAppInstance().getSharedPreferences(
        getAppInstance().getString(R.string.learn_pref_name),
        Context.MODE_PRIVATE
    )
    private var mSharedPreferencesEditor: SharedPreferences.Editor

    init {
        mSharedPreferencesEditor = mSharedPreferences.edit()
        mSharedPreferencesEditor.apply()
    }

    fun setValue(key: String, value: Any?) {
        when (value) {
            is Int? -> {
                mSharedPreferencesEditor.putInt(key, value!!)
                mSharedPreferencesEditor.apply()
            }

            is String? -> {
                mSharedPreferencesEditor.putString(key, value!!)
                mSharedPreferencesEditor.apply()
            }

            is Double? -> {
                mSharedPreferencesEditor.putString(key, value.toString())
                mSharedPreferencesEditor.apply()
            }

            is Long? -> {
                mSharedPreferencesEditor.putLong(key, value!!)
                mSharedPreferencesEditor.apply()
            }

            is Boolean? -> {
                mSharedPreferencesEditor.putBoolean(key, value!!)
                mSharedPreferencesEditor.apply()
            }
        }
    }

    fun getStringValue(key: String, defaultValue: String = ""): String {
        return mSharedPreferences.getString(key, defaultValue)!!
    }

    fun HashMap<String, ArrayList<Room>>.roomsToJsonString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    fun saveRoomHashMapToSharedPreferences(
        context: Context,
        key: String,
        roomHashMap: HashMap<String, ArrayList<Room>>
    ) {
        val jsonString = roomHashMap.roomsToJsonString()
        mSharedPreferencesEditor.putString(key, jsonString)
        mSharedPreferencesEditor.apply()
    }

    fun getRoomHashMapFromSharedPreferences(
        context: Context,
        key: String
    ): HashMap<String, ArrayList<Room>> {
        val jsonString = mSharedPreferences.getString(key, null)

        val gson = Gson()
        return if (jsonString != null) {
            gson.fromJson(
                jsonString,
                object : TypeToken<HashMap<String, ArrayList<Room>>>() {}.type
            )
        } else {
            hashMapOf()
        }
    }


    // Convert the HashMap to a JSON string
    fun HashMap<String, Boolean>.booleansToJsonString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    // Store the JSON string in SharedPreferences
    fun saveBooleanHashMapToSharedPreferences(
        context: Context,
        key: String,
        booleanHashMap: HashMap<String, Boolean>
    ) {
        val jsonString = booleanHashMap.booleansToJsonString()
        mSharedPreferencesEditor.putString(key, jsonString)
        mSharedPreferencesEditor.apply()
    }

    // Retrieve and parse the JSON string from SharedPreferences
    fun getBooleanHashMapFromSharedPreferences(
        context: Context,
        key: String
    ): HashMap<String, Boolean> {
        val jsonString = mSharedPreferences.getString(key, null)
        val gson = Gson()
        return if (jsonString != null) {
            gson.fromJson(jsonString, object : TypeToken<HashMap<String, Boolean>>() {}.type)
        } else {
            hashMapOf()
        }
    }


    fun getIntValue(key: String, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    fun getLongValue(key: String, defaultValue: Long): Long {
        return mSharedPreferences.getLong(key, defaultValue)
    }

    fun getBooleanValue(keyFlag: String, defaultValue: Boolean = false): Boolean {
        return mSharedPreferences.getBoolean(keyFlag, defaultValue)
    }

    fun removeKey(key: String) {
        mSharedPreferencesEditor.remove(key)
        mSharedPreferencesEditor.apply()
    }

    fun clear() {
        mSharedPreferencesEditor.clear().apply()
    }
}