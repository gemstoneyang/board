package hello.board.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PostRepositoryTest {

    PostRepository repository = new PostRepository();

    @AfterEach
    void afterEach() {
        repository.clearStore();
    }

    @Test
    void save() {
        Post post = new Post("subject", "hello", "user1", "1234");
        Post result = repository.save(post);
        assertThat(result.getSubject()).isEqualTo(post.getSubject());
        assertThat(result.getContents()).isEqualTo(post.getContents());
        assertThat(result.getNickname()).isEqualTo(post.getNickname());
        assertThat(result).isEqualTo(post);
    }

    @Test
    void findById() {
        Post post = new Post("subject", "hello", "user1", "1234");
        Post savedPost = repository.save(post);

        Post result = repository.findById(savedPost.getId());

        assertThat(result).isEqualTo(savedPost);

    }

    @Test
    void findAll() {
        Post post1 = repository.save(new Post("subject", "hello", "user1", "1234"));
        Post post2 = repository.save(new Post("subject2", "hello2", "user2", "1234"));

        List<Post> result = repository.findAll();

        assertThat(result).contains(post1, post2);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void update() {
        Post post = new Post("subject", "hello", "user1", "1234");
        Post savedPost = repository.save(post);

        Post updateParam = new Post("update subject", "hi", "user1", "2345");
        Post result = repository.update(savedPost.getId(), updateParam);

        assertThat(result.getSubject()).isEqualTo(updateParam.getSubject());
        assertThat(result).isEqualTo(savedPost);
        assertThat(result.getUploadTime()).isEqualTo(savedPost.getUploadTime());
    }

    @Test
    void delete() {
        Post savedPost = repository.save(new Post("subject", "hello", "user1", "1234"));
        repository.delete(savedPost.getId());
        Post result = repository.findById(savedPost.getId());
        assertThat(result).isNull();
    }
}