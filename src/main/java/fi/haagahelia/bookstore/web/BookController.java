package fi.haagahelia.bookstore.web;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.CategoryRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository drepository;


    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // show all the books
    @RequestMapping(value = { "/", "/booklist" })
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // restful serviece to get all books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) repository.findAll();
    }

    // reset service to get all book by id
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return repository.findById(bookId);
    }

    // add a new book
    @RequestMapping(value = "/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categorys", drepository.findAll());
        return "addbook";
    }

    // save new book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        repository.save(book);
        return "redirect:booklist";
    }

    // delete book
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

}
