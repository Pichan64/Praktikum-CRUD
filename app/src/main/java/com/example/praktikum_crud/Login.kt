package com.example.praktikum_crud

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    var TxUsername: EditText? = null
    var TxPassword: EditText? = null
    var BtnLogin: Button? = null
    var dbHelper: DBHelper? = null
    private val html: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        TxUsername = findViewById<View>(R.id.txUsername) as EditText
        TxPassword = findViewById<View>(R.id.txPassword) as EditText
        BtnLogin = findViewById<View>(R.id.btnLogin) as Button
        dbHelper = DBHelper(this)
        val tvCreateAccount = findViewById<View>(R.id.tvCreateAccount) as TextView
        tvCreateAccount.text =
            Html.fromHtml("Saya tidak punya akun." + "</font><font color='#3b5998'>create one</font>")
        tvCreateAccount.setOnClickListener {
            startActivity(
                Intent(
                    this@Login,
                    Register::class.java
                )
            )
        }
        BtnLogin!!.setOnClickListener {
            val username = TxUsername!!.text.toString().trim { it <= ' ' }
            val password = TxPassword!!.text.toString().trim { it <= ' ' }
            val res = dbHelper!!.checkUser(username, password)
            if (res == true) {
                Toast.makeText(this@Login, "Berhasil Login", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Login, MainActivity::class.java))
            } else {
                Toast.makeText(this@Login, "Login Gagal", Toast.LENGTH_SHORT).show()
            }
        }
        var fromHtml: Spanned
        String
        html
        run {
            val result: Spanned
            result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(Html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(html)
            }
            return result
        }
    }
}