package com.example.barberapp.ui.appointments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barberapp.models.BookManager
import com.example.barberapp.models.BookModel

class AppointmentViewModel : ViewModel() {

    private val booksList = MutableLiveData<List<BookModel>>()

    val observableBooksList: LiveData<List<BookModel>>
        get() = booksList

    init {
        load()
    }

    fun load() {
        booksList.value = BookManager.findAll()
    }
}