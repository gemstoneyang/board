package hello.board;

import hello.board.domain.Post;
import hello.board.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestPostInit {

    private final PostRepository postRepository;

    @PostConstruct
    public void init() {
        postRepository.save(new Post("Test Subject", "hello", "user", "1234"));
    }
}
