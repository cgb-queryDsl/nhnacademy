package com.nhnmart.cs.controller;

import com.nhnmart.cs.domain.Category;
import com.nhnmart.cs.domain.Customer;
import com.nhnmart.cs.domain.Post;
import com.nhnmart.cs.domain.dto.CustomerPostRegisterRequest;
import com.nhnmart.cs.exception.PostNotFoundException;
import com.nhnmart.cs.exception.ValidationFailedException;
import com.nhnmart.cs.repository.CustomerRepository;
import com.nhnmart.cs.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final String SESSION_ID = "sessionId";
    private static final String UPLOAD_DIR = "/Users/gyeongseo/Downloads/";

    private final PostRepository postRepository;
    private final CustomerRepository customerRepository;

    public CustomerController(PostRepository postRepository, CustomerRepository customerRepository) {
        this.postRepository = postRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public String customerIndex(HttpServletRequest request, Model model) {
        String customerId = extractedCustomerId(request);

        Customer customer = customerRepository.getCustomer(customerId);
        List<Post> postList = postRepository.getPostListByCustomer(customer.getId());

        model.addAttribute("customerName",  customer.getName());
        model.addAttribute("postList", postList);
        return "customerIndex";
    }

    @GetMapping("/registerPost")
    public String postForm() {
        return "postRegisterForm";
    }

    @PostMapping
    public String postRegister(@Valid @ModelAttribute CustomerPostRegisterRequest customerPostRegisterRequest,
                            BindingResult bindingResult, HttpServletRequest request) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        String customerId = extractedCustomerId(request);
        Customer customer = customerRepository.getCustomer(customerId);
        List<String> tmp = extractedFileName(customerPostRegisterRequest);
        String writeTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        postRepository.register(customer,
                                customerPostRegisterRequest.getTitle(),
                                Category.valueOf(customerPostRegisterRequest.getCategory()),
                                customerPostRegisterRequest.getContent(),
                                writeTime,
                                tmp.toArray(new String[tmp.size()]));

        return "redirect:/customer";
    }

    @GetMapping("/postList/{postId}")
    public String viewPost(@PathVariable("postId") Long postId, Model model, HttpServletRequest request) {
        if (Objects.isNull(postRepository.getPost(postId))) {
            throw new PostNotFoundException(postId);
        }

        Post post = postRepository.getPost(postId);
        String customerIdByPost = post.getCustomer().getId();
        String loginCustomerId = extractedCustomerId(request);

        if (!loginCustomerId.equals(customerIdByPost)) {
            return "noAuthorized";
        }

        String[] files = post.getFiles();

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "viewPost";
    }

    @ResponseBody
    @GetMapping("/images/{fileName}")
    public Resource showImage(@PathVariable String fileName) throws MalformedURLException {
        return new UrlResource("file:" + UPLOAD_DIR + fileName);
    }

    private String extractedCustomerId(HttpServletRequest request) {
        String sessionId = (String) request.getSession().getAttribute(SESSION_ID);
        String customerId = sessionId.split("-")[0];
        return customerId;
    }

    private List<String> extractedFileName(CustomerPostRegisterRequest customerPostRegisterRequest) throws IOException {
        List<String> tmp = new ArrayList<>();

        if (customerPostRegisterRequest.getFile().get(0).getSize() != 0) {
            for (MultipartFile multipartFile : customerPostRegisterRequest.getFile()) {
                multipartFile.transferTo(Paths.get(UPLOAD_DIR + multipartFile.getOriginalFilename()));
                tmp.add(multipartFile.getOriginalFilename());
            }
        }
        return tmp;
    }
}
