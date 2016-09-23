package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.Category;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookstore(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			
			//adding some categories
			crepository.save(new Category("Sci-Fi"));
			crepository.save(new Category("IT"));
			crepository.save(new Category("BIT"));
			crepository.save(new Category("Thriller"));
			crepository.save(new Category("Biography"));
			
			//adding some books
			repository.save(new Book("Spring Boot in action", "Walls, Craig", 2016, "1-61729-254-0", 35.5, crepository.findByName("IT").get(0)));
			repository.save(new Book("Maintainable Javascript", "Zakas, Nicholas C.", 2012, "1-61729-738-1", 45.9, crepository.findByName("IT").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
}
}
