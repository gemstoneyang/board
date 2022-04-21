package hello.board.controller;

import hello.board.domain.Post;
import hello.board.domain.PostRepository;
import hello.board.form.AddForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("post", postRepository.findById(postId));
        return "post";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("form", new AddForm());
        return "addForm";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute AddForm form, RedirectAttributes redirectAttributes) {
        Post savedPost = postRepository.save(new Post(form.getSubject(), form.getContents(), form.getNickname(), form.getPassword()));
        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/mainpage/view/{postId}";
    }
}
