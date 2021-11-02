package com.example.noteappfirebase

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentHome : Fragment() {

    private val myViewModel by lazy{ ViewModelProvider(this).get(MyViewModel::class.java)}

    lateinit var rvMain: RecyclerView
    lateinit var rvAdapter: NoteAdapter
    lateinit var etnote: EditText
    lateinit var addBtn: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)


        etnote = view.findViewById(R.id.etnote)
        addBtn = view.findViewById(R.id.addBtn)

        rvMain = view.findViewById(R.id.rvMain)
        rvAdapter = NoteAdapter(this)
        rvMain.adapter = rvAdapter
        rvMain.layoutManager = LinearLayoutManager(activity)

        myViewModel.getData()

        myViewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> rvAdapter.update(notes)
        })

        addBtn.setOnClickListener {
            val note = etnote.text.toString()
            myViewModel.addNote(Note("",note))
            etnote.setText("")
        }

        return view
    }


    fun editAlert( idNote: String, note:String){
        val dialogBuilder = activity?.let { androidx.appcompat.app.AlertDialog.Builder(it) }
        val input = EditText(activity)
        var newNote = ""
        input.setText("$note")
        dialogBuilder?.setMessage("")?.setPositiveButton("Save", DialogInterface.OnClickListener { _, _ ->
            newNote = input.text.toString()
           // myViewModel.editNote(idNote,newNote)
        })?.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.cancel()
        })
        val alert = dialogBuilder?.create()

        alert?.setTitle("Edit Alert")
        alert?.setView(input)
        alert?.show()


    }

    fun deleteAlert( idNote: String){
        val dialogBuilder = activity?.let { androidx.appcompat.app.AlertDialog.Builder(it) }

        dialogBuilder?.setMessage("Confirm delete ?")
            ?.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->
                myViewModel.deleteNote(idNote)
            })?.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ -> dialog.cancel()
        })
        val alert = dialogBuilder?.create()

        alert?.setTitle("Delete Alert")
        alert?.show()


    }

}