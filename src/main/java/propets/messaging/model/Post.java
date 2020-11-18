package propets.messaging.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(of = { "id" })
@ToString
@Document(collection = "message")
public class Post implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2130869903194972275L;
	@Id
	String id;
	String userLogin;
	@Setter
	String userName;
	@Setter
	String avatar;
	LocalDateTime datePost;
	@Setter
	String text;
	Set<String> images;
	
	public Post() {
		datePost = LocalDateTime.now();
		images = new HashSet<>();
	}

	public Post(String userLogin, String userName, String avatar, String text, Set<String> images) {
		this();
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.text = text;
		this.images = images.stream()
							.collect(Collectors.toSet());
	}
	
	public boolean addImage(String image) {
		return images.add(image);
	}
	
	public boolean removeImage(String image) {
		return images.remove(image);
	}
}
