package com.example.barberapp.ui.appointments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.barberapp.R
import com.example.barberapp.adapters.BookAdapter
import com.example.barberapp.databinding.FragmentAppointmentsBinding
import com.example.barberapp.main.BookXApp
import com.example.barberapp.models.BookModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AppointmentFragment : Fragment() {

    lateinit var app: BookXApp
    private var _fragBinding: FragmentAppointmentsBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var appointmentViewModel: AppointmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentAppointmentsBinding.inflate(inflater, container, false)
        val root = fragBinding.root

        fragBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)
        appointmentViewModel.observableBooksList.observe(viewLifecycleOwner, Observer {
                books ->
            books?.let { render(books) }
        })

//        val fab: FloatingActionButton = fragBinding.fab
//        fab.setOnClickListener {
//            val action = AppoimtmentFragmentDirections.actionAppointmentFragmentToBookFragment()
//            findNavController().navigate(action)
//        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appointment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    private fun render(booksList: List<BookModel>) {
        fragBinding.recyclerView.adapter = BookAdapter(booksList)//,this)
        if (booksList.isEmpty()) {
            fragBinding.recyclerView.visibility = View.GONE
           // fragBinding.booksNotFound.visibility = View.VISIBLE
        } else {
            fragBinding.recyclerView.visibility = View.VISIBLE
           // fragBinding.booksNotFound.visibility = View.GONE
        }
    }
//
//    override fun onBookClick(book: BookModel) {
//        val action = AppoimtmentFragmentDirections.actionAppointmentFragmentToBookDetailFragment(book.id)
//        findNavController().navigate(action)
//    }

    override fun onResume() {
        super.onResume()
        appointmentViewModel.load()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}