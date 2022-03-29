package com.example.wishlist02.controllers;

import com.example.wishlist02.repository.Wish;
import com.example.wishlist02.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {
    WishRepository wishRepository = new WishRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/allelister")
    public String alleLister(){
        return "allelister";
    }

    @GetMapping("/listeOprettelse")
    public String listeOprettelse(){
        return "listeOprettelse";
    }

    @PostMapping("/opret")
    public String opretListe(WebRequest dataFromForm){
        System.out.println(dataFromForm.getParameter("titel"));
        System.out.println(dataFromForm.getParameter("beskrivelse"));
        System.out.println(dataFromForm.getParameter("pris"));
        Wish newWish = new Wish(dataFromForm.getParameter("titel"), dataFromForm.getParameter("beskrivelse"), Integer.parseInt(dataFromForm.getParameter("pris")));
        System.out.println(newWish.toString());
        wishRepository.saveWishToDB(newWish);

        return "redirect:/";
    }

}
