package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BlogSoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSoftwareApplication.class, args);
	}

	@RestController
	public static class BlogController{

		@GetMapping("/")
		public String home() {
			return "<!DOCTYPE html>\n" +
					"<html lang=\"en\">\n" +
					"  <head>\n" +
					"    <title>My Blog</title>\n" +
					"  </head>\n" +
					"  <body>\n" +
					"    <h1>My First Blog Post</h1>\n" +
					"    <p>The first blog post I've written on my blogging software!</p>\n" +
					"  </body>\n" +
					"</html>";
		}
	}
}
