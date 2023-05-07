package my.edu.tarc.ass2.Dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.ass2.R
import my.edu.tarc.ass2.databinding.ActivityMainBinding

class Dashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}