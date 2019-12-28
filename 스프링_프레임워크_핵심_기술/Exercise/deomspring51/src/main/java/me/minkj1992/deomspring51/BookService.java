package me.minkj1992.deomspring51;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    BookRepository bookRepository;

////     생성자
//    @Autowired
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
    
////      setter
//    @Autowired
//    public void setBookRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

}
