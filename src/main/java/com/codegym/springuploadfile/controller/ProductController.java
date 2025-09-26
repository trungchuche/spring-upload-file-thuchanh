package com.codegym.springuploadfile.controller;

import com.codegym.springuploadfile.model.Product;
import com.codegym.springuploadfile.model.ProductForm;
import com.codegym.springuploadfile.service.IProductService;
import com.codegym.springuploadfile.service.ProductService;
import com.codegym.springuploadfile.util.BadWordFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {


    private final IProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @Value("${file-upload}")
    private String fileUpload;

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm){
        String feedback = productForm.getFeedback();
        String author = productForm.getAuthor();
        if (BadWordFilter.containsBadWord(feedback)) {
            ModelAndView errorView = new ModelAndView("/error");
            errorView.addObject("message", "Feedback chứa từ ngữ không phù hợp!");
            errorView.addObject("author", author);
            errorView.addObject("feedback", feedback);
            return errorView;
        }
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        }catch (IOException ex){
            ex.printStackTrace();
        }
        Product product = new Product(productForm.getId(), productForm.getName(), productForm.getDescription(), fileName, author, feedback);
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        modelAndView.addObject("message", "New product created successfully");
        return modelAndView;
    }
}
