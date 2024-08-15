package com.booknook_mz.BookNook;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Getting Requests & Returning responses

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins="http://localhost:3000/")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping
    public ResponseEntity<List<Book>> getSomeBooks(){
        List<Book> allBooks = bookService.allBooks();
        List<Book> someBooks = allBooks.subList(0,51);
        System.out.println(someBooks.size());
        return new ResponseEntity<>(someBooks, HttpStatus.OK);
    }

    @GetMapping("/{bookId}")//search by id
    public ResponseEntity<Optional<Book>> getSingleBook(@PathVariable String bookId){//PathVariable means whatever getting from PathVariable convert to objectid
        return new ResponseEntity<Optional<Book>>(bookService.singleBook(bookId),HttpStatus.OK);
    }
}
