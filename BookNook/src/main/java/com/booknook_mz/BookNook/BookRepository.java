package com.booknook_mz.BookNook;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//deals with database

@Repository
public interface BookRepository extends MongoRepository<Book, ObjectId> {

    Optional<Book> findBookByBookId(String bookId);
}
