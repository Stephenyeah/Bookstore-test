package fi.haagahelia.bookstore;

import fi.haagahelia.bookstore.domain.Book;
import fi.haagahelia.bookstore.domain.BookRepository;
import fi.haagahelia.bookstore.domain.Category;
import fi.haagahelia.bookstore.domain.CategoryRepository;
import fi.haagahelia.bookstore.domain.User;
import fi.haagahelia.bookstore.domain.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	/**
	 * Save demo users, courses and students to H2 DB
	 * 
	 * @param brepository
	 * @return
	 */

	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository drepository,
			UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of category");
			Stream.of("Fiction", "Mystery", "Romance", "Science Fiction", "Fantasy", "Biography").forEach(name -> {
				drepository.save(new Category(name));
			});

			log.info("save a couple of books");
			brepository.save(new Book("The Old Man and the Sea", "Ernest Hemingway", 1952, 97819075 - 9027 - 6,
					drepository.findByName("Fiction").get(0)));
			brepository.save(new Book("Romance of the Three Kingdoms", "Luo Guanzhong", 1907, 8951346,
					drepository.findByName("Fiction").get(0)));

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2", "ADMIN");
			urepository.saveAll(Arrays.asList(user1, user2));
		};
	}
}
