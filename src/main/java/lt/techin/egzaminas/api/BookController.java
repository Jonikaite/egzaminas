package lt.techin.egzaminas.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookRepository {

    private final BookRepository bookRepository;
    private final SubbookRepository subbookRepository;

    private final String applicationVersion;

    //    @Autowired, nuo 4.3
    public BookRepository(BookRepository bookRepository, SubbookRepository subbookRepository,
                            @Value("${application.version}") String applicationVersion) {


        this.bookRepository = bookRepository;
        this.subbookRepository = subbookRepository;
        this.applicationVersion = applicationVersion;
    }


    //GET /api/v1
    @GetMapping("/info")
    public String getInfo() {
        return applicationVersion;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/{BookId}/subbooks")
    // "/api/v1/books/1/subbooks"
    public SubBook createSubBook(@PathVariable Long BookId, @RequestBody SubBook subBook) {
        var Book = bookRepository.findById(BookId)
                .orElseThrow(() -> new NullPointerException(String.format("Book with id %s Not found", BookId)));

        subBook.setBook(Book);

        return subbookRepository.save(subBook);
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookRepository.findAll();
//        return bookRepository.findAll()
//                .stream().map()
//        return List.of();
    }

    @GetMapping("/{BookId}/subbooks")
    public List<SubBook> getSubBooks(@PathVariable Long BookId) {
        //return subbookRepository.findAll(); //FIXME improve findAllWhereBookIs

        var Book = bookRepository.findById(BookId)
                .orElseThrow(() -> new NullPointerException(String.format("Book with id %s Not found", BookId)));

        return subbookRepository.findAllByBook(Book); //FIXME improve findAllWhereBookIs
    }

}
