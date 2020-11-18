package propets.messaging.service;

import java.util.List;
import java.util.Set;

import propets.messaging.dto.NewPostDto;
import propets.messaging.dto.PostDto;
import propets.messaging.dto.ViewPostsDto;

public interface MessagingService {
	
	PostDto createPost(String login, String userName, String avatar, NewPostDto newPostDto);
	
	PostDto updatePost(String id, NewPostDto newPostDto);
	
	PostDto deletePost(String id);
	
	PostDto getPost(String id);
	
	ViewPostsDto viewPosts(int itemsOnPage, int currentPage);
	
	List<PostDto> getPosts(Set<String> postsId);
}
