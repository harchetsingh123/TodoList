package com.harchet.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class ToDoAdapter( private val todos: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {      // adapter takes in stuff which will be used to fill the recyclic views



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        // what happens when view holder is created
        // ofcourse we want it to look like the item view we were talking bout
        return ToDoViewHolder(
            // use layout inflator which has just an xml file( not id)
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,parent,false
            )

        )
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun addTodo( todo:ToDo){
        // todo has to be some empty array
        todos.add(todo)
        notifyItemChanged(todos.size-1)


    }

    fun deleteDoneTodos(){
        todos.removeAll { // goes to each element of the list
            todo->  // todo is the iterator 
             todo.isChecked
        }
    }

    private fun toggleStrikeThrough(tvTodoTitle:TextView , isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags= tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }
        else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    } // toggling the checkbox

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curr= todos[position]
        holder.tv.text= curr.title
        holder.cb.isChecked = curr.isChecked
        toggleStrikeThrough(holder.tv,curr.isChecked)
        holder.cb.setOnCheckedChangeListener { _, isChecked ->
            toggleStrikeThrough(holder.tv,isChecked)
            curr.isChecked = !curr.isChecked
        } // _ means we dont need that paramenter here
    }

    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
      val tv = itemView.findViewById<TextView>(R.id.tvToDotitle)
        var cb= itemView.findViewById<CheckBox>(R.id.cbDone)
    }

}