package com.cryptotracker.cryptotracker.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suyog.contactlistapp.databinding.ContactsLayoutBinding
import com.suyog.contactlistapp.model.Contact

class ContactsAdapter(val context: Context):RecyclerView.Adapter<ContactsAdapter.getViewHolder>() {

    interface onItemClickInterface{
        fun onItemClick(contact:Contact)
    }


    private lateinit var onItemClick:onItemClickInterface

    fun setOnItemClickInterface(click: Any){
        onItemClick = click as onItemClickInterface
    }

    private var list:ArrayList<Contact> =  ArrayList<Contact>()

    inner class getViewHolder(val binding: ContactsLayoutBinding,val onItemClicked:onItemClickInterface):RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {

            binding.ContactName.text = list[position].name
            binding.Category.text = list[position].category
            binding.ContactNumber.text = list[position].mobile

            binding.root.setOnClickListener {
                onItemClick.onItemClick(list[position])
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): getViewHolder {
        return getViewHolder(ContactsLayoutBinding.inflate(LayoutInflater.from(context),parent,false),onItemClick)
    }

    override fun onBindViewHolder(holder: getViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = list.size

    fun updateList(updatedList: List<Contact>){
        if(updatedList.isNullOrEmpty()){
            list.clear()
        }else{
            list = updatedList as ArrayList<Contact>
        }

        notifyDataSetChanged()
    }

}