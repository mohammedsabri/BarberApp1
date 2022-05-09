package com.example.barberapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.barberapp.firebase.FirebaseDBManager
import com.example.barberapp.firebase.FirebaseImageManager
import com.example.barberapp.models.BookManager
import com.example.barberapp.models.BookModel
import com.google.firebase.auth.FirebaseUser
import timber.log.Timber

class BookViewModel : ViewModel() {

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addBook(firebaseUser: MutableLiveData<FirebaseUser>,
                book: BookModel) {
        status.value = try {

            //book.profilepic = FirebaseImageManager.imageUri.value.toString()
            FirebaseDBManager.create(firebaseUser,book)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

}