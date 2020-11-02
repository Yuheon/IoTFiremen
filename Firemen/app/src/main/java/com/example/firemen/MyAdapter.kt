package com.example.firemen

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.dialog_layout.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import kotlinx.android.synthetic.main.remove_dialog.*
import kotlinx.android.synthetic.main.remove_dialog.view.*


class MyAdapter(context: Context) : RecyclerView.Adapter<MyViewHolder>() {
    lateinit var recyclerItemList: MutableList<RecyclerItem>
    var context : Context = context
    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemViewHolder = inflater.inflate(R.layout.recycler_item, parent, false)
        this.parent = parent


        return MyViewHolder(itemViewHolder, this).apply{
            itemView.deleteButton.setOnClickListener{
                //recyclerItemList.removeAt(adapterPosition)
                //notifyItemRemoved(adapterPosition)

                val inflater = LayoutInflater.from(context)
                var rootView:View = inflater.inflate(R.layout.remove_dialog, parent, false)

                //val builder = androidx.appcompat.app.AlertDialog.Builder(context)
                    //.setView(inflater.inflate(R.layout.remove_dialog, parent, false))

                var builder = AlertDialog.Builder(context).setView(rootView)
                //var builder = AlertDialog.Builder(context).setView(inflater.inflate(R.layout.remove_dialog, parent, false))<<하면 망함
                rootView.removeCheck.text = "${recyclerItemList[adapterPosition].address} 을(를) 삭제하시겠습니까?"
                val alertDialog = builder.show()

                //builder.setMessage(" 삭제하시겠습니까?")
                //builder.setPositiveButton("확인", null)
                //builder.setNegativeButton("취소",null)

                rootView.yesRemove.setOnClickListener{
                    recyclerItemList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                    alertDialog.dismiss()
                }
                rootView.noRemove.setOnClickListener {
                    alertDialog.dismiss()
                }

            }

        }
        return MyViewHolder(itemViewHolder, this)
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = recyclerItemList[position]
        holder.itemView.address_view.text = currentItem.address
        holder.itemView.fireState.setImageResource(currentItem.fireImage)

//        holder.apply{
//            itemView.deleteButton.setOnClickListener{
//
    }
}