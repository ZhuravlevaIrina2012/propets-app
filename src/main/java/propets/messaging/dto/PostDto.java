package propets.messaging.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
	String id;
	String userLogin;
	String userName;
	String avatar;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	LocalDateTime datePost;
	String text;
	@Singular
	Set<String> images;
}
