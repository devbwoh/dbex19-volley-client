package kr.ac.kumoh.s20210000.gunplaapplication
// package는 본인의 것 사용

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import kr.ac.kumoh.s20210000.gunplaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val model: GunplaViewModel by viewModels()
        model.requestMechanic()
    }
}