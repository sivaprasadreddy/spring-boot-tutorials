/**
 * 
 */
package com.sivalabs.demo;

import static com.sivalabs.demo.jooq.domain.tables.Posts.POSTS;
import static com.sivalabs.demo.jooq.domain.tables.Comments.COMMENTS;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivalabs.demo.entities.Comment;
import com.sivalabs.demo.entities.Post;
import com.sivalabs.demo.jooq.domain.tables.records.CommentsRecord;
import com.sivalabs.demo.jooq.domain.tables.records.PostsRecord;

/**
 * @author Siva
 *
 */
@Service
@Transactional
public class BlogService
{
	@Autowired
	private DSLContext dsl;
	
	public Post createPost(Post post){
		PostsRecord postsRecord = dsl.insertInto(POSTS)
				.set(POSTS.TITLE, post.getTitle())
				.set(POSTS.CONTENT, post.getContent())
				.set(POSTS.CREATED_ON, post.getCreatedOn())
				.returning(POSTS.ID)
				.fetchOne();
			
		post.setId(postsRecord.getId());
		return post;
	}
	
	public List<Post> getAllPosts(){		
		List<Post> posts = new ArrayList<>();		
		Result<Record> result = dsl.select().from(POSTS).fetch();
		for (Record r : result) {
		    posts.add(getPostEntity(r));
		}
		return posts ;
	}

	public Post getPost(Integer postId){
		Record record = dsl.select().
								from(POSTS)
								.where(POSTS.ID.eq(postId))
								.fetchOne();
		if(record != null)
		{
		    Post post = getPostEntity(record);
		    
		    Result<Record> commentRecords = dsl.select().
										from(COMMENTS)
										.where(COMMENTS.POST_ID.eq(postId))
										.fetch();
		    
		    for (Record r : commentRecords) {
			    post.addComment(getCommentEntity(r));
			}
		    return post;
		}
		return null;
	}
	
	
	public Comment createComment(Comment comment){
		CommentsRecord commentsRecord = dsl.insertInto(COMMENTS)
				.set(COMMENTS.POST_ID, comment.getPost().getId())
				.set(COMMENTS.NAME, comment.getName())
				.set(COMMENTS.EMAIL, comment.getEmail())
				.set(COMMENTS.CONTENT, comment.getContent())
				.set(COMMENTS.CREATED_ON, comment.getCreatedOn())
				.returning(COMMENTS.ID)
				.fetchOne();
			
		comment.setId(commentsRecord.getId());
		return comment;
	}
	
	public void deleteComment(Integer commentId){
		dsl.deleteFrom(COMMENTS)
				.where(COMMENTS.ID.equal(commentId))
				.execute();
	}
	
	private Post getPostEntity(Record r){
		Integer id = r.getValue(POSTS.ID, Integer.class);
	    String title = r.getValue(POSTS.TITLE, String.class);
	    String content = r.getValue(POSTS.CONTENT, String.class);
	    Timestamp createdOn = r.getValue(POSTS.CREATED_ON, Timestamp.class);
	    return new Post(id, title, content, createdOn);
	}
	
	private Comment getCommentEntity(Record r) {
		Integer id = r.getValue(COMMENTS.ID, Integer.class);
		Integer postId = r.getValue(COMMENTS.POST_ID, Integer.class);
	    String name = r.getValue(COMMENTS.NAME, String.class);
	    String email = r.getValue(COMMENTS.EMAIL, String.class);
	    String content = r.getValue(COMMENTS.CONTENT, String.class);
	    Timestamp createdOn = r.getValue(COMMENTS.CREATED_ON, Timestamp.class);
	    return new Comment(id, postId, name, email, content, createdOn);
	}
}
