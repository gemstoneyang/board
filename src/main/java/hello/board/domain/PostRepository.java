package hello.board.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Repository
public class PostRepository {

    private static Map<Long, Post> store = new HashMap<>();
    private static Long sequence = 0L;

    //등록
    public Post save(Post post) {
        Long id = ++sequence;
        post.setId(id);

        store.put(id, post);
        return post;
    }

    //조회
    public Post findById(Long id) {
        return store.get(id);
    }

    //전부 조회
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }

    //수정
    public Post update(Long id, Post updateParam) {
        Post post = store.get(id);

        post.setSubject(updateParam.getSubject());
        post.setContents(updateParam.getSubject());
        post.setPassword(updateParam.getPassword());
        post.setEditTime(LocalDateTime.now());

        return post;
    }

    //삭제
    public void delete(Long id) {
        if (store.containsKey(id)) {
            store.remove(id);
        }
    }


    //테스트용 초기화
    public void clearStore() {
        store.clear();
    }
}
