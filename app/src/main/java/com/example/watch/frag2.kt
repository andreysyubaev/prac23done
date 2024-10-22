package com.example.watch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import android.view.View
import android.widget.Toast

class frag2 : WearableActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag2)
    }
    fun take_result (view: View)
    {
        Toast.makeText(this, "готово", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, main::class.java)
        startActivity(intent)
    }
}