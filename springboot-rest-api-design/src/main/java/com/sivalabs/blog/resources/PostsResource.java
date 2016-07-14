/**
 * 
 */
package com.sivalabs.blog.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.sivalabs.blog.entities.Post;
import com.sivalabs.blog.model.PostDTO;
import com.sivalabs.blog.utils.BeanCopyUtils;

/**
 * @author Siva
 *
 */
public class PostsResource extends AbstractCollectionResource<Post, PostDTO> implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	public PostsResource(Page<Post> pageData)
	{
		super(pageData);
	}

	@Override
	protected List<PostDTO> populateDTOs(List<Post> content)
	{
		List<PostDTO> dtos = new ArrayList<>();
		for (Post post : content)
		{
			//dtos.add(new PostDTO(post));
			dtos.add(BeanCopyUtils.toPostDTO(post));
		}
		return dtos;
	}
	
}
