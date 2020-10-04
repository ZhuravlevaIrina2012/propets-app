package propets.messaging.service;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import propets.messaging.dao.MessagingRepository;
import propets.messaging.dto.NewPostDto;
import propets.messaging.dto.PostDto;
import propets.messaging.dto.ViewPostsDto;
import propets.messaging.exceptions.PostNotFoundException;
import propets.messaging.model.Post;

@Service
public class MessagingServiceImpl implements MessagingService {

	@Autowired
	MessagingRepository messagingRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(String login, NewPostDto newPostDto) {
		Post post = new Post(login, newPostDto.getUserName(), newPostDto.getAvatar(), newPostDto.getText(), newPostDto.getImages());
		messagingRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(String id, NewPostDto newPostDto) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		String avatar = newPostDto.getAvatar();
		if (avatar != null) {
			post.setAvatar(avatar);
		}
		String name = newPostDto.getUserName();
		if (name != null) {
			post.setUserName(name);
		}
		String text = newPostDto.getText();
		if (text != null) {
			post.setText(text);
		}
		Set<String> images = newPostDto.getImages();
		if (!images.isEmpty()) {
			images.forEach(post::addImage);
		}
		messagingRepository.save(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		messagingRepository.delete(post);
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto getPost(String id) {
		Post post = messagingRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public ViewPostsDto viewPosts(int itemsOnPage, int currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPosts(Set<String> postsId) {
		// TODO Auto-generated method stub
		return null;
	}

}
