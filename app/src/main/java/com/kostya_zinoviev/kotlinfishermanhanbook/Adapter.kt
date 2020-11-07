package com.kostya_zinoviev.kotlinfishermanhanbook

import android.app.LauncherActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*
import org.w3c.dom.Text

class Adapter (val context: Context,var list:ArrayList<Model>):RecyclerView.Adapter<Adapter.ViewHolder>() {

    var listR:ArrayList<Model> = list
    val contextR = context

    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val mainImageView = view.findViewById<ImageView>(R.id.mainImageView)
        val titleTextView  = view.findViewById<TextView>(R.id.title)
        val descriptionTextView = view.findViewById<TextView>(R.id.shortDescription)

        fun bind(listItem: Model, context: Context){
            mainImageView.setImageResource(listItem.mainImageView)
            titleTextView.text = listItem.title
            descriptionTextView.text = listItem.description
            itemView.setOnClickListener(){
                Toast.makeText(context,"Item ${listItem.title}",Toast.LENGTH_SHORT).show()
                val intent = Intent(context,ContentAvtivity::class.java).apply {
                    putExtra("Title",listItem.title)
                    putExtra("Description",listItem.description)
                    putExtra("ImageView",listItem.mainImageView)
                }
                context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val inflaterR = LayoutInflater.from(contextR)
        val view:View = inflaterR.inflate(R.layout.item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  listR.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listItem = listR[position]
        holder.bind(listItem,contextR)
    }

    fun updateAdapter(listArray:List<Model>){
        listR.clear()
        listR.addAll(listArray)
        notifyDataSetChanged()
    }
}