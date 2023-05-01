package uz.pdp.bookmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.bookmarket.payload.ApiResponse;
import uz.pdp.bookmarket.payload.BookDto;
import uz.pdp.bookmarket.service.BookService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PreAuthorize(value = "hasAnyAuthority('ADD_BOOK')")
    @PostMapping("/add")
    public HttpEntity<?> addBook(@Valid @RequestBody BookDto bookDto) {
        ApiResponse apiResponse = bookService.addBook(bookDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAnyAuthority('EDIT_BOOK')")
    @PutMapping("/edit/{id}")
    public HttpEntity<?> editBook(@PathVariable Long id, @RequestBody BookDto bookDto){
        ApiResponse apiResponse = bookService.editBook(id, bookDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
