package fi.haagahelia.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;

@DataJpaTest
public class JPABookRepositoryTest{
	@Autowired
	private BookRepository repository;
	@Autowired
	private CategoryRepository drepository;

	@Test 
	public void findByNameShouldReturnBook(){
		List<Book>books = repository.findByAuthor("Ernest Hemingway");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getTitle()).isEqualTo("The Old Man and the Sea");
	}

    @Test
    public void createNewBook(){     
		Category category = new Category("Mystery");
		drepository.save(category);

        Book book = new Book("Mickey","Mouse",1968,783434343,category);
        
		repository.save(book);
    }
	@Test
	public void deleteNewBook(){
		List<Book> books = repository.findByAuthor("Ernest Hemingway");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByAuthor("Ernest Hemingway");
		assertThat(newBooks).hasSize(0);
	}

	

}