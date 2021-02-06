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
    private lateinit var adapter: GunplaAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = GunplaAdapter2()
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

    inner class GunplaAdapter2(): RecyclerView.Adapter<GunplaAdapter2.ViewHolder>() {

        inner class ViewHolder(itemView: View):
            RecyclerView.ViewHolder(itemView) {
            val text: TextView = itemView.findViewById(android.R.id.text1)

            init {
                itemView.setOnClickListener {
                    //onClick()
                }
            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                android.R.layout.simple_list_item_1,
                parent,
                false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = model.getGunpla(position).toString()
        }

        override fun getItemCount(): Int {
            return model.getSize()
        }
    }
}