package com.example.wishlist02.controllers;

import com.example.wishlist02.Model.User;
import com.example.wishlist02.Model.Wish;
import com.example.wishlist02.Model.WishList;
import com.example.wishlist02.repository.UserRepository;
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
    UserRepository userRepository = new UserRepository();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/Lists")
    public String alleLister(Model m) {
        m.addAttribute("allWishLists", wishListRepository.getAllWishLists());
        return "all-lists";
    }

    @GetMapping("/createUser")
    public String createUser(){
        return "create-user";
    }

    @GetMapping("/list")
    public String getSingleListById(@RequestParam String id, Model m) {

        m.addAttribute("wishes", wishRepository.getWishesFromWishlistID(id));
        wishListRepository.setActiveWishList(wishListRepository.getWishlistByID(Integer.parseInt(id)));

        //Kode der fetcher fra databasen, baseret på id
        //Put data på template
        return "single-list";
    }

    @PostMapping("/createWishAndAddToWishList")
    public String createWish(WebRequest dataFromForm) {
        Wish newWish = new Wish(dataFromForm.getParameter("titel"), dataFromForm.getParameter("beskrivelse"), Integer.parseInt(dataFromForm.getParameter("pris")));
        int wishID = wishRepository.saveWishToDB(newWish, wishListRepository.getActiveWishList().getWishList_ID());
        newWish.setWishID(wishID);

        return "redirect:/" + wishListRepository.getActiveWishList().getUrl();
    }

    @PostMapping("/createWishList")
    public String createList(WebRequest dataFromForm) {
        WishList newWishList = new WishList(dataFromForm.getParameter("wishListName"));
        wishListRepository.setActiveWishList(newWishList);
        wishListRepository.saveWishListToDB();
        newWishList.setWishList_ID(wishListRepository.getLastCreatedWishListID());
        newWishList.generateUrl();
        wishListRepository.addToAllWishlists(newWishList);
        wishListRepository.setActiveWishList(newWishList);
        return "redirect:/" + wishListRepository.getActiveWishList().getUrl();
    }

    @PostMapping("/createUser")
    public String createUser(WebRequest userData) {

        String username = userData.getParameter("username");
        String password = userData.getParameter("password");
        String firstname = userData.getParameter("firstname");
        String lastname = userData.getParameter("lastname");
        String email = userData.getParameter("email");
        String phoneNumber = userData.getParameter("phone-number");

        User newUser = new User(username,password,firstname,lastname,email,phoneNumber);
        userRepository.createUser(newUser);
        return "redirect:/";

    }
    @PostMapping("/deleteWish")
    public String deleteWish(@RequestParam String id){


    return "1";}


}
