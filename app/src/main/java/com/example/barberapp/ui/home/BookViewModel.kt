package com.example.barberapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barberapp.models.BookManager
import com.example.barberapp.models.BookModel

class BookViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addBook(book: BookModel) {
        status.value = try {
            BookManager.create(book)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

}