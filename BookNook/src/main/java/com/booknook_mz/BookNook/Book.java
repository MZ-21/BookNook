package com.booknook_mz.BookNook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;//class represents each document in book collection
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "books-c")
@Data //takes care of getter, setter, toString methods
@AllArgsConstructor //annotation for creating constructor that takes all properties
@NoArgsConstructor

public class Book {
    @Id //lets framework know that this property should be treated as unique identifier
    private ObjectId id;
    private String bookId;
    private String title;
    private String author;
    private Double rating;
    private List<String> genres;
    private String publisher;
    private String description;
    private String coverImg;
}

