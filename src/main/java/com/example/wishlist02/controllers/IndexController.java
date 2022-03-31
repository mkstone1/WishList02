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
    @GetMapping("/Lists")
    public String alleLister(Model m){
       // wishListRepository.getAllWishLists();
      //  m.addAttribute("allWishlistsName",wishListRepository.getAllWishlistName());
      //  m.addAttribute("allWishlistsID" , wishListRepository.getAllWishlistID());
        m.addAttribute("allWishLists",wishListRepository.getAllWishLists());
        return "allelister";
    }

    @GetMapping("/list")
    public String getSingleListById(@RequestParam String id, Model m){

        m.addAttribute("wishes", wishListRepository.getWishesFromWishlistID(id));
        wishListRepository.setActiveWishList(wishListRepository.getWishlistByID(Integer.parseInt(id)));

        //Kode der fetcher fra databasen, baseret på id
        //Put data på template
        return "single-list";
    }

    /*@GetMapping("/listeOprettelse")
    public String listeOprettelse(Model m){

        return "listeOprettelse";
    }*/

    @PostMapping("/createWishAndAddToWishList")
    public String createWish(WebRequest dataFromForm){
        Wish newWish = new Wish(dataFromForm.getParameter("titel"), dataFromForm.getParameter("beskrivelse"), Integer.parseInt(dataFromForm.getParameter("pris")));
        int wishID = wishRepository.saveWishToDB(newWish, wishListRepository.getActiveWishList().getWishList_ID());

        return "redirect:/"+ wishListRepository.getActiveWishList().getUrl();
    }

    @PostMapping("/createWishList")
    public String createList(WebRequest dataFromForm){
        WishList newWishList = new WishList(dataFromForm.getParameter("wishListName"));
        wishListRepository.setActiveWishList(newWishList);
        wishListRepository.saveWishListToDB();
        newWishList.setWishList_ID(wishListRepository.getLastCreatedWishListID());
        newWishList.generateUrl();
        wishListRepository.addToAllWishlists(newWishList);
        wishListRepository.setActiveWishList(newWishList);
        return "redirect:/"+wishListRepository.getActiveWishList().getUrl();
    }


}
