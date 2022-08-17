package com.example.demo.Service;

import com.example.demo.domain.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    @Autowired
    BookRepository bookRepository;
    public List<Book> get() {
        return bookRepository.getBooks();

    }

    public Book getOne(int Book_id) {
        return bookRepository.getBook(Book_id);
    }


    public Book create(Book book){

        if(book.getName() == null || book.getName() == "")
            throw new IllegalArgumentException("Name cannot be empty");

        int book_id = bookRepository.create(book.getName(),book.getSynopsis(),book.getCategory_id());
        return bookRepository.getBook(book_id);
    }

    public void update(Book book) {

        if(book.getName() == null || book.getName() == "")
            throw new IllegalArgumentException("Name cannot be empty");

        bookRepository.update(
                book.getBook_id(),
                book.getName(),
                book.getSynopsis(),
                book.getCategory_id());
    }

    public void delete(int Book_id) {
        bookRepository.delete(Book_id);
    }

}
