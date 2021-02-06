package kr.ac.kumoh.s20210000.gunplaapplication
// package는 본인의 것 사용

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.kumoh.s20210000.gunplaapplication.databinding.ActivityMainBinding
// package는 본인의 것 사용

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // Delegated Property
    private val model: GunplaViewModel by viewModels()
    private lateinit var adapter: GunplaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GunplaAdapter(model) { mechanic -> adapterOnClick(mechanic) }
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
        //Toast.makeText(this, mechanic.model, Toast.LENGTH_SHORT).show()
        val uri = Uri.parse("https://www.youtube.com/results?search_query=${mechanic.model}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}