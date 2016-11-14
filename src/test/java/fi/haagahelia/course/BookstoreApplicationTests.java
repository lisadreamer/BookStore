package fi.haagahelia.course;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.course.web.BookController;
import fi.haagahelia.course.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookstoreApplicationTests {

	@Autowired
    private BookController controller;
	private UserController ucontroller;
	
	@Test
	public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
        assertThat(ucontroller).isNotNull();
    }	

}
