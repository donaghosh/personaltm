package com.example.myapplication

import android.app.ActionBar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.assigned_dialog.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var mDatabase:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.add).setOnClickListener {
            val title = dialog_title.text.toString()
            val description = dialog_description.text.toString()

            val uid = FirebaseAuth.getInstance().uid

            FirebaseDatabase.getInstance().reference.child(uid!!).child("assigned")
                .push()
                .setValue(Tasks(title, description))

            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}
