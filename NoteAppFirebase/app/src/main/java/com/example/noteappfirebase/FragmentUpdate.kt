package com.example.noteappfirebase


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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FragmentUpdate : Fragment() {

lateinit var etNewNote: EditText
lateinit var updateBtn: Button

private val myViewModel by lazy{ ViewModelProvider(this).get(MyViewModel::class.java)}
var myID =""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        etNewNote = view.findViewById(R.id.etNewNote)
        updateBtn = view.findViewById(R.id.updateBtn)

        myID = arguments?.getString("Id").toString()
        updateBtn.setOnClickListener {
            var note = etNewNote.text.toString()
            Log.d("CheckMyID", note )
            myViewModel.editNote(myID,note)
            view?.let { Navigation.findNavController(it).navigate(R.id.action_fragmentUpdate_to_fragmentHome) }
        }

        return view
    }



}