package propets.messaging.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewPostsDto {
	int itemsOnPage;
	int currentPage;
	int itemsTotal;
	@Singular
	List<PostDto> posts;
}
