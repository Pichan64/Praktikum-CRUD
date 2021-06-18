package com.example.praktikum_crud

import android.content.ContentValues
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
import com.example.praktikum_crud.R.id
import com.example.praktikum_crud.Register

class Register : AppCompatActivity() {
    var TxUsername: EditText? = null
    var TxPassword: EditText? = null
    var TxConPassword: EditText? = null
    var BtnRegister: Button? = null
    var dbHelper: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        dbHelper = DBHelper(this)
        TxUsername = findViewById<View>(id.txUsernameReg) as EditText
        TxPassword = findViewById<View>(id.txPasswordReg) as EditText
        TxConPassword = findViewById<View>(id.txConPassword) as EditText
        BtnRegister = findViewById<View>(id.btnRegister) as Button
        val tvRegister = findViewById<View>(id.tvRegister) as TextView
        tvRegister.text = fromHtml("Kembali" + "</font><font color='#3b5998'>Login</font>")
        tvRegister.setOnClickListener { startActivity(Intent(this@Register, Login::class.java)) }
        BtnRegister!!.setOnClickListener {
            val username = TxUsername!!.text.toString().trim { it <= ' ' }
            val password = TxPassword!!.text.toString().trim { it <= ' ' }
            val conPassword = TxConPassword!!.text.toString().trim { it <= ' ' }
            val values = ContentValues()
            if (password != conPassword) {
                Toast.makeText(this@Register, "Password tidak sama", Toast.LENGTH_SHORT).show()
            } else if (password == "" || username == "") {
                Toast.makeText(this@Register, "Username or Password tidak sama", Toast.LENGTH_SHORT)
                    .show()
            } else {
                values.put(DBHelper.row_username, username)
                values.put(DBHelper.row_password, password)
                dbHelper!!.insertData(values)
                Toast.makeText(this@Register, "Registerasi Berhasil", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    companion object {
        fun fromHtml(html: String?): Spanned {
            val result: Spanned
            result = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html)
            } else {
                Html.fromHtml(html)
            }
            return result
        }
    }
}