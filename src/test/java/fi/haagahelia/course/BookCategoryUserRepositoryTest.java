package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
    private BookRepository brep;
	private CategoryRepository crep;
	private UserRepository urep;
	
	@Test
    public void createNewBook() {
    	Book book = new Book("Linux for dummies", "Blum, Richard", 2009, "1-75312-254-9", 27.02, crep.findByName("IT").get(0));
    	brep.save(book);
    	assertThat(book.getId()).isNotNull();
    } 
	
	@Test
    public void createNewCategory() {
		Category category = new Category("Sociology");
    	crep.save(category);
    	assertThat(category.getCategoryId()).isNotNull();
    }

	@Test
    public void createNewUser() {
		User user = new User("accountant", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "accountant@gmail.com");
    	urep.save(user);
    	assertThat(user.getId()).isNotNull();
    }
	
	@Test
    public void findByIsbnShouldReturnBook() {
        List<Book> books = brep.findByIsbn("1-61729-254-0");
        
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("Spring Boot in action");
    }
	
	@Test
    public void findByNameShouldReturnCategory() {
        List<Category> cat = crep.findByName("IT");
        
        assertThat(cat).hasSize(1);
        assertThat(cat.get(0).getName()).isEqualTo("IT");
        assertThat(cat.get(0).getName()).isNotNull();
    }
	
	@Test
    public void findByUsernameShouldReturnUser() {
        User user = urep.findByUsername("user");
        
        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
        assertThat(user.getUsername()).isEqualTo("user");        
    }
	
	@Test
    public void deleteBook() {
		Book book = new Book("Linux for dummies 2", "Blum, Richard", 2009, "9-99999-999-9", 27.02, crep.findByName("IT").get(0));
    	brep.save(book);    	
    	assertThat(book.getId()).isNotNull();
    	
    	//test delete functionality for the book we just created
    	Long bookid = book.getId();
    	brep.delete(bookid);
    	Book b = brep.findOne(bookid);
    	assertThat(b).isNull();
    } 
	
	@Test
    public void deleteCategory() {
		Category category = new Category("Children");
    	crep.save(category);
    	assertThat(category.getCategoryId()).isNotNull();
    	
    	//test delete functionality for the category we just created
    	Long categid = category.getCategoryId();
    	brep.delete(categid);
    	Category c = crep.findOne(categid);
    	assertThat(c).isNull();
    } 
	
	@Test
    public void deleteUser() {
		User user = new User("user9", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user9@gmail.com");
    	urep.save(user);
    	assertThat(user.getId()).isNotNull();
    	
    	//test delete functionality for the user9 we just created
    	Long userid = user.getId();
    	urep.delete(userid);
    	User u = urep.findOne(userid);
    	assertThat(u).isNull();
    } 
}
