package kr.ac.kumoh.s20210000.gunplaapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GunplaAdapter(var model: GunplaViewModel
//,
//    private val onClick: (GunplaViewModel.Mechanic) -> Unit
): RecyclerView.Adapter<GunplaAdapter.ViewHolder>() {

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