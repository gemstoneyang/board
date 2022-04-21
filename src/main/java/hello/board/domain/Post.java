package hello.board.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter @Setter
public class Post {

    private Long id;

    private String subject;
    private String contents;
    private String nickname;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime uploadTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime editTime;

    public Post(String subject, String contents, String nickname, String password) {
        this.subject = subject;
        this.contents = contents;
        this.nickname = nickname;
        this.password = password;

        this.uploadTime = LocalDateTime.now();
        this.editTime = LocalDateTime.now();
    }
}
