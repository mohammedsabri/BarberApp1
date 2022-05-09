package com.example.barberapp.ui.home

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.barberapp.R
import com.example.barberapp.auth.LoggedInViewModel
import com.example.barberapp.databinding.FragmentBookBinding
import com.example.barberapp.main.BookXApp
import com.example.barberapp.models.BookModel
import com.example.barberapp.ui.appointments.AppointmentFragment
import java.text.SimpleDateFormat


class BookFragment : Fragment() {

    lateinit var app: BookXApp
    private var _fragBinding: FragmentBookBinding? = null
    private val fragBinding get() = _fragBinding!!
    private lateinit var bookViewModel: BookViewModel
    private val loggedInViewModel : LoggedInViewModel by activityViewModels()
    //lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = activity?.application as BookXApp
        setHasOptionsMenu(true)
        //navController = Navigation.findNavController(activity!!, R.id.nav_host_fragment)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragBinding = FragmentBookBinding.inflate(inflater, container, false)
        val root = fragBinding.root
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.observableStatus.observe(viewLifecycleOwner, Observer {
                status -> status?.let { render(status) }
        })
        activity?.title = getString(R.string.action_appointments)
        val spinner: Spinner = fragBinding.spinner
// Create an ArrayAdapter using the string array and a default spinner layout
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.time_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }

        //fragBinding.recyclerView.setLayoutManager(LinearLayoutManager(activity))
        //fragBinding.recyclerView.adapter = AppointmentAdapter(app.appointmentsStore.findAll())
        setButtonListener(fragBinding)
        return root
    }

    private fun render(status: Boolean) {
        when (status) {
            true -> {
                view?.let {
                    //Uncomment this if you want to immediately return to Report
                    //findNavController().popBackStack()
                }
            }
            false -> Toast.makeText(context,getString(R.string.bookTitle),Toast.LENGTH_LONG).show()
        }
    }

    fun setButtonListener(layout: FragmentBookBinding) {


            layout.navBook.setOnClickListener {
                val amount = 15.00
                val appDate = layout.calendarView3.date
                val appTime = layout.spinner.selectedItem
                layout.textView2.text = amount.toString()
                layout.textView3.text = amount.toString()

                val format = SimpleDateFormat("yyyy.MM.dd")
                val date = format.format(appDate)

                bookViewModel.addBook(loggedInViewModel.liveFirebaseUser,
                    BookModel(
                        appDate = date, time = appTime as String,
                        cost = 15, barbername = "Moe",
                        service = "Haircut",
                        email = loggedInViewModel.liveFirebaseUser.value?.email!!,                    )//loggedInViewModel.liveFirebaseUser.value?.email!!)
                )

        }
    }




    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_appointment, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController()) || super.onOptionsItemSelected(item)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AppointmentFragment().apply {
                arguments = Bundle().apply { }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragBinding = null
    }
}