package hello.board.controller;

import hello.board.domain.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/mainpage")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/view")
    public String mainView(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "main-page";
    }

    @GetMapping("/view/{postId}")
    public String postView(@PathVariable Long postId, Model model) {
        log.info("postview");
        model.addAttribute("post", postRepository.findById(postId));
        return "post";
    }

//    @GetMapping("/add")
//    public String addForm() {
//        return "addForm";
//    }
}
