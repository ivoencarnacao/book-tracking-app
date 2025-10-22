package dev.ivoencarnacao.service.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.ivoencarnacao.service.dto.BookDetailDto;
import dev.ivoencarnacao.service.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;

  }

  @GetMapping
  public String listAllBooks(Model model) {

    List<BookDetailDto> allBooks = bookService.getAllBooks();
    model.addAttribute("allBooks", allBooks);

    return "books/books-list";

  }

}
