package com.example.wishlist02.controllers;

import com.example.wishlist02.repository.Wish;
import com.example.wishlist02.repository.WishList;
import com.example.wishlist02.repository.WishListRepository;
import com.example.wishlist02.repository.WishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {
    WishRepository wishRepository = new WishRepository();
    WishListRepository wishListRepository = new WishListRepository();

    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/AllLists")
    public String alleLister(Model m){
        m.addAttribute("allWishlists",wishListRepository.getAllWishLists());
        return "allelister";
    }

    @GetMapping("/listeOprettelse")
    public String listeOprettelse(){
        return "listeOprettelse";
    }

    @PostMapping("/createWishAndAddToWishList")
    public String createWish(WebRequest dataFromForm){
        Wish newWish = new Wish(dataFromForm.getParameter("titel"), dataFromForm.getParameter("beskrivelse"), Integer.parseInt(dataFromForm.getParameter("pris")));
        int wishID = wishRepository.saveWishToDB(newWish, wishListRepository.getActiveWishList().getWishList_ID());
        return "redirect:/listeOprettelse";
    }

    @PostMapping("/createWishList")
    public String createList(WebRequest dataFromForm){
        WishList newWishList = new WishList(dataFromForm.getParameter("wishListName"));
        wishListRepository.setActiveWishList(newWishList);
        wishListRepository.saveWishListToDB();
        newWishList.setWishList_ID(wishListRepository.getLastCreatedWishListID());
        return "redirect:/listeOprettelse";
    }

   /* @RequestMapping(value = "/getWishlist/{WishlistID}", method = RequestMethod.POST)
    public String getWishlist(@PathVariable("wishListID") String wishlistId, Model model){
        model.addAttribute("wishlist",wishListRepository.getActiveWishList().getWishList_ID());
        return "WishList";

        https://www.netjstech.com/2018/08/spring-mvc-example-with-pathvaribale-annotation.html
    }*/

}
