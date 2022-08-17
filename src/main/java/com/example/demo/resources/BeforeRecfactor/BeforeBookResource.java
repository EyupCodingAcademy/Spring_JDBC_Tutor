package com.example.demo.resources.BeforeRecfactor;

import com.example.demo.Service.BeforeRefactor.BeforeBookService;
import com.example.demo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/old_books")
public class BeforeBookResource {

    /*
    @GetMapping("/")
    public String get() {
        return "Hello World";
    }

}*/

    @Autowired
    BeforeBookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Book>> get() {
        List<Book> books = bookService.get();


        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{Book_id}")
    public ResponseEntity<Book> get(HttpServletRequest request, @PathVariable("Book_id") int Book_id)
    {
        Book book = bookService.getOne(Book_id);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Book> create(HttpServletRequest request,@RequestBody Map<String,Object> bookMap) {
        String name = (String) bookMap.get("name");
        String synopsis = (String) bookMap.get("synopsis");
        int category_id = (int) bookMap.get("category_id");

        Book book = bookService.create(name,synopsis,category_id);
        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @PutMapping("/update/{Book_id}")
    public ResponseEntity<Map<String,Boolean>> update(HttpServletRequest request,
                                                      @PathVariable("Book_id") Integer Book_id,
                                                      @RequestBody Book book) {
        book.setBook_id(Book_id);
        bookService.update(book);
        Map<String,Boolean> map = new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Book_id}")
    public ResponseEntity<Map<String,Boolean>> delete(HttpServletRequest request, @PathVariable("Book_id") Integer Book_id) {
        bookService.delete(Book_id);
        Map<String,Boolean> map = new HashMap<>();
        map.put("success",true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
}
