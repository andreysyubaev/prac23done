    package com.example.watch

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.widget.Button
    import android.widget.Toast

    class main: AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }
        fun take_result(view: View) {
            Toast.makeText(this, "Registr", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, frag::class.java)
            startActivity(intent)
        }
    }