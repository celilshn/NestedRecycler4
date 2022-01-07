package com.cengcelil.nestedrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    var list_deep = arrayListOf<ParentItem>()
    var list = DataProvider.getParentItems().toMutableList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.rec)
        list.forEachIndexed() { i, v ->
            list_deep.add(list[i].copy())
        }
        val adapter = ParentAdapter(list_deep)
        recyclerView.adapter = adapter
        adapter.submitList(list)
    }
}