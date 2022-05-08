package com.example.barberapp.models

import timber.log.Timber


var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BookMemStore : BookStore {

    val books = ArrayList<BookModel>()

    override fun findAll(): List<BookModel> {
        return books
    }

    override fun findById(id: Long): BookModel? {
        val foundBook: BookModel? = books.find { it.id == id }
        return foundBook
    }

    override fun create(book: BookModel) {
        book.id = getId()
        books.add(book)
        logAll()
    }

    fun logAll() {
        Timber.v("** Appointments List **")
//        appointments.forEach { Timber.v("Appointment ${it}") }
    }
}