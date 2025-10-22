package dev.ivoencarnacao.service.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.ivoencarnacao.service.dto.BookFormDto;
import dev.ivoencarnacao.service.dto.UserBookDetailDto;
import dev.ivoencarnacao.service.service.UserBookService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users/{username}/books")
public class UserBookController {

  private final UserBookService userBookService;

  public UserBookController(UserBookService userBookService) {
    this.userBookService = userBookService;
  }

  @GetMapping
  @PreAuthorize("#username == authentication.name")
  public String getUserBooks(@PathVariable String username,
      Model model) {

    List<UserBookDetailDto> userBooks = userBookService.getUserBooks(username);
    model.addAttribute("userBooks", userBooks);
    model.addAttribute("username", username);

    return "users/users-list";

  }

  @GetMapping("/new")
  @PreAuthorize("#username == authentication.name")
  public String showNewBookForm(@PathVariable String username,
      Model model) {

    model.addAttribute("bookFormDto", new BookFormDto("", "", "", null));

    return "books/book-new";

  }

  @PostMapping
  @PreAuthorize("#username == authentication.name")
  public String addUserBook(@PathVariable String username,
      @Valid @ModelAttribute("bookFormDto") BookFormDto bookFormDto,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "books/book-new";
    }

    userBookService.addUserBook(bookFormDto, username);

    return "redirect:/users/" + username + "/books";

  }

  @PostMapping("/{userBookId}")
  @PreAuthorize("#username == authentication.name")
  public String updateUserBookStatus(
      @PathVariable String username,
      @PathVariable Long userBookId,
      @RequestParam(defaultValue = "false") boolean isFavorite,
      @RequestParam(defaultValue = "false") boolean isWishlist,
      @RequestParam(defaultValue = "false") boolean isBucketlist) {

    userBookService.updateUserBookStatus(userBookId, isFavorite, isWishlist, isBucketlist, username);

    return "redirect:/users/" + username + "/books";

  }

  @PostMapping("/{userBookId}/delete")
  @PreAuthorize("#username == authentication.name")
  public String removeUserBook(@PathVariable String username,
      @PathVariable Long userBookId) {

    userBookService.removeUserBook(userBookId, username);

    return "redirect:/users/" + username + "/books";

  }

}
