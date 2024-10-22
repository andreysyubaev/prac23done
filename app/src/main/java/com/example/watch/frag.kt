package com.example.watch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class frag : WearableActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var buttonSubmit: Button
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag)
        editText1 = findViewById(R.id.ed1)
        editText2 = findViewById(R.id.ed2)
        buttonSubmit = findViewById(R.id.button1)
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE)
        buttonSubmit.setOnClickListener {
            take_result(it)
        }
    }
    fun take_result(view: View) {
        try {
            val isRegistered = sharedPreferences.getBoolean("isRegistered", false)
            if (isRegistered) {
                editText1.setText("")
                editText2.setText("")
            }

            if (validateFields()) {
                val savedLogin = sharedPreferences.getString("login", "")
                val savedPassword = sharedPreferences.getString("password", "")

                val login = editText1.text.toString().trim()
                val password = editText2.text.toString().trim()

                if (isRegistered) {
                    if (savedLogin == login && savedPassword == password) {
                        Toast.makeText(this, "Вы зарегистрированы!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, frag2::class.java))
                    } else {
                        Toast.makeText(this, "Неверный логин или пароль!", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (password.length >= 9) {
                        saveData(login, password)
                        Toast.makeText(this, "Вы зарегистрированы!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, frag2::class.java))
                    } else {
                        Toast.makeText(this, "Пароль должен быть не меньше 8 символов!", Toast.LENGTH_SHORT).show()
                        saveErrorMessage("Пароль должен быть не меньше 8 символов!")
                    }
                }
            } else {
                Toast.makeText(this, "Пожалуйста заполните все поля!", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateFields(): Boolean {
        val text1 = editText1.text.toString().trim()
        val text2 = editText2.text.toString().trim()

        return !TextUtils.isEmpty(text1) && !TextUtils.isEmpty(text2)
    }

    private fun saveData(login: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("login", login)
        editor.putString("password", password)
        editor.putBoolean("isRegistered", true)
        editor.apply()
    }
    private fun saveErrorMessage(message: String) {
        val editor = sharedPreferences.edit()
        editor.putString("errorMessage", message)
        editor.apply()
    }
}
