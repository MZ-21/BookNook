package com.booknook_mz.BookNook;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shelf")
@CrossOrigin(origins="http://localhost:3000/")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @PostMapping
    public ResponseEntity<Shelf> createShelf(@RequestBody Map<String, Object> payload) {
        String shelfName = payload.get("shelfName").toString();
        String icon = payload.get("icon").toString();
        List<Book> bookCollection = new ArrayList<>();
        Map<String, Object> bookMap = (Map<String, Object>) payload.get("bookCollection");

        Book b = createBook(bookMap);
        bookCollection.add(b);
        return new ResponseEntity<Shelf>(shelfService.createShelf(shelfName, icon, bookCollection), HttpStatus.CREATED);
    }

    @GetMapping("/shelfNames")
    public ResponseEntity<List<String>> getShelfNames(){
        List<Shelf> allShelves = shelfService.allShelves();
        List<String> shelfNames = new ArrayList<>();
        for(Shelf shelf : allShelves) {
            shelfNames.add(shelf.getShelfName());
        }
        return new ResponseEntity<>(shelfNames, HttpStatus.OK);
    }

    @DeleteMapping("/deleteShelf")
    public void deleteShelf(@RequestBody Map<String, String> payload){
        shelfService.deleteShelf(payload.get("shelfName"));
    }

    @PostMapping("/addBook")
    public ResponseEntity<Shelf> addBook(@RequestBody Map<String, Object> payload) {
        String shelfName = payload.get("shelfName").toString();
        Map<String, Object> bookMap = (Map<String, Object>) payload.get("book");

        Book book = createBook(bookMap);

        return new ResponseEntity<Shelf>(shelfService.addBookToShelf(shelfName, book), HttpStatus.OK);
    }

    public Book createBook(Map<String, Object> bookMap) {
        Book book = new Book();
        ObjectId id = new ObjectId(bookMap.get("id").toString());
        book.setId(id);
        book.setBookId((String) bookMap.get("bookId"));
        book.setTitle((String) bookMap.get("title"));
        book.setAuthor((String) bookMap.get("author"));
        Number ratingValue = (Number) bookMap.get("rating");
        book.setRating(ratingValue != null ? ratingValue.doubleValue() : null);
        book.setDescription((String) bookMap.get("description"));
        book.setPublisher((String) bookMap.get("publisher"));
        book.setCoverImg((String) bookMap.get("coverImg"));
        book.setGenres((List<String>) bookMap.get("genres"));

        return book;
    }
}

