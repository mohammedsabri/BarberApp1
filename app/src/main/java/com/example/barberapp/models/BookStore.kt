package com.example.barberapp.models

interface BookStore {
    fun findAll() : List<BookModel>
    fun findById(id: Long) : BookModel?
    fun create(book: BookModel)
}