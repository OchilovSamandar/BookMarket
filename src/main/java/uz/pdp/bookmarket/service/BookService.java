package uz.pdp.bookmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Service;
import uz.pdp.bookmarket.entity.Book;
import uz.pdp.bookmarket.payload.ApiResponse;
import uz.pdp.bookmarket.payload.BookDto;
import uz.pdp.bookmarket.repository.BookRepository;

import java.util.Optional;

@Service
@EnableJpaAuditing
public class BookService {
    @Autowired
    BookRepository bookRepository;


    public ApiResponse addBook(BookDto bookDto) {
        boolean existsByNameAndAuthor = bookRepository.existsByNameAndAuthor(bookDto.getName(), bookDto.getAuthor());
        if (existsByNameAndAuthor){
            return new ApiResponse("Bunday book mavjud",false);
        }

        Book book = new Book(
                bookDto.getName(),
                bookDto.getAuthor(),
                bookDto.getDescription(),
                bookDto.getPrice()
        );
        bookRepository.save(book);
        return new ApiResponse("Book saqlandi",true);

    }

    public ApiResponse editBook(Long id, BookDto bookDto) {
        boolean existsById = bookRepository.existsById(id);
        if (existsById){
            Optional<Book> byId = bookRepository.findById(id);
            Book book = byId.get();

            book.setName(bookDto.getName());
            book.setAuthor(bookDto.getAuthor());
            book.setDescription(bookDto.getDescription());
            book.setPrice(bookDto.getPrice());

            bookRepository.save(book);
            return new ApiResponse("Book succesfully edited",true);
        }
        return new ApiResponse("Book not found",false);

    }
}
