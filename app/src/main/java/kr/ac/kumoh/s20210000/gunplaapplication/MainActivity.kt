package kr.ac.kumoh.s20210000.gunplaapplication
// package는 본인의 것 사용

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.kumoh.s20210000.gunplaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Delegated Property
    private val model: GunplaViewModel by viewModels()
    private lateinit var adapter: GunplaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GunplaAdapter(model)
        binding.list.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@MainActivity.adapter
        }

        // import androidx.lifecycle.Observer
        model.list.observe(this, {
            adapter.notifyDataSetChanged()
        })

        model.requestMechanic()
    }

    private fun adapterOnClick(mechanic: GunplaViewModel.Mechanic) {
        Toast.makeText(this, mechanic.model, Toast.LENGTH_SHORT).show()
    }
}