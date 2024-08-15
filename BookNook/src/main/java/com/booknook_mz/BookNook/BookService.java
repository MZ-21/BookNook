package com.booknook_mz.BookNook;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired //want framework to instantiate class
    private BookRepository bookRepo;

    @Autowired
    private BookRepository bookRepository;

    public List<Book> allBooks(){
        return bookRepo.findAll();
    }

    public Optional<Book> singleBook(String bookId){
        return bookRepository.findBookByBookId(bookId);
    }
}
