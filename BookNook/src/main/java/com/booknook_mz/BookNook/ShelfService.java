package com.booknook_mz.BookNook;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//coordinates activities btwn different parts of the application
@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepo;

    public Shelf createShelf(String shelfName, String icon, List<Book> bookCollection){
        return shelfRepo.insert(new Shelf(shelfName, icon, bookCollection));

    }

    public void deleteShelf(String shelfName){
        shelfRepo.deleteById(getShelfId(shelfName));
    }

    public List<Shelf> allShelves(){
        return shelfRepo.findAll();
    }

    public ObjectId getShelfId(String shelfName){
        List<Shelf> listShelves = shelfRepo.findAll();
        ObjectId shelfId = null;

        for(Shelf shelf : listShelves){
            if(shelf.getShelfName().equals(shelfName)){
                shelfId = shelf.getShelfId();
            }
        }
        return shelfId;
    }


    public Shelf addBookToShelf(String shelfName, Book bookObj){
        System.out.println("in shelf ser");
        Shelf foundShelf =  shelfRepo.findById(getShelfId(shelfName)).orElseThrow(() -> new IllegalArgumentException("Shelf not found"));
        System.out.println("foundShelf");
        System.out.println(foundShelf);
        foundShelf.getBookCollection().add(bookObj);
        shelfRepo.save(foundShelf);
        //foundShelf.addBook(bookObj);


        System.out.println(foundShelf);


        return foundShelf;
    }
}
//Holds business logic & orchestrates operations btwn different parts of application
//Repo layer deals with data access and persistence
//the Object/Model layer deals with data & business logic?
