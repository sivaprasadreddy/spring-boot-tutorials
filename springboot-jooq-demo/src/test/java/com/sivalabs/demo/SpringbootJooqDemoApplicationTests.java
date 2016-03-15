package com.sivalabs.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sivalabs.demo.entities.Comment;
import com.sivalabs.demo.entities.Post;

/**
 * @author Siva
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootJooqDemoApplication.class)
public class SpringbootJooqDemoApplicationTests
{

	@Autowired
	private BlogService blogService;
	
	@Test
	public void findAllPosts()  {
		List<Post> posts = blogService.getAllPosts();
		assertNotNull(posts);
		assertTrue(!posts.isEmpty());
		for (Post post : posts)
		{
			System.err.println(post);
		}
	}
	
	@Test
	public void findPostById()  {
		Post post = blogService.getPost(1);
		assertNotNull(post);
		System.out.println(post);
		List<Comment> comments = post.getComments();
		System.out.println(comments);
		
	}
	
	@Test
	public void createPost() {
		Post post = new Post(0, "My new Post", "This is my new test post", new Timestamp(System.currentTimeMillis()));
		Post savedPost = blogService.createPost(post);
		Post newPost = blogService.getPost(savedPost.getId());
		assertEquals("My new Post", newPost.getTitle());
		assertEquals("This is my new test post", newPost.getContent());
	}
	
	@Test
	public void createComment() {
		Integer postId = 1;
		Comment comment = new Comment(0, postId, "User4", "user4@gmail.com", "This is my new comment on post1", new Timestamp(System.currentTimeMillis()));
		Comment savedComment = blogService.createComment(comment);
		Post post = blogService.getPost(postId);
		List<Comment> comments = post.getComments();
		assertNotNull(comments);
		for (Comment comm : comments)
		{
			if(savedComment.getId() == comm.getId()){
				assertEquals("User4", comm.getName());
				assertEquals("user4@gmail.com", comm.getEmail());
				assertEquals("This is my new comment on post1", comm.getContent());
			}
		}
		
	}
	
}
