package com.example.wishlist02.controllers;

import com.example.wishlist02.repository.Wish;
import com.example.wishlist02.repository.WishList;
import com.example.wishlist02.repository.WishListRepository;
import com.example.wishlist02.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {
    WishRepository wishRepository = new WishRepository();
    WishListRepository wishListRepository = new WishListRepository();

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

    @PostMapping("/create")
    public String createWish(WebRequest dataFromForm){
        Wish newWish = new Wish(dataFromForm.getParameter("titel"), dataFromForm.getParameter("beskrivelse"), Integer.parseInt(dataFromForm.getParameter("pris")));
        wishListRepository.getActiveWishList().addWish(newWish);
        int wishID = wishRepository.saveWishToDB(newWish);
        wishListRepository.saveWishListToDB(wishID);
        return "redirect:/listeOprettelse";
    }

    @PostMapping("/createList")
    public String createList(WebRequest dataFromForm){
        WishList newWishList = new WishList(dataFromForm.getParameter("wishListName"));
        newWishList.getName();
        wishListRepository.setActiveWishList(newWishList);
        return "redirect:/listeOprettelse";
    }

}
