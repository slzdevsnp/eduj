package com.ini28minutes.springboot.basics.springbootin10steps;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BooksController {

    private List<Book> storedBooks = fillBooks();


    //first rest service created
    @GetMapping("/books")
    public List<Book> getallBooks(){
        return storedBooks;
    }

    private List<Book> fillBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L,"Mastering spring 5.2 1st ed", "Ranga Karanam"));
        books.add(new Book(2L, "Malaya Zemlya", "L. I. Brezhnev"));
        return books;
    }
}
