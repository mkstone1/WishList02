package com.example.wishlist02.controllers;

import com.example.wishlist02.Model.User;
import com.example.wishlist02.Model.Wish;
import com.example.wishlist02.Model.WishList;
import com.example.wishlist02.repository.UserRepository;
import com.example.wishlist02.repository.WishListRepository;
import com.example.wishlist02.repository.WishRepository;
import com.example.wishlist02.services.UserServices;
import com.example.wishlist02.services.WishListServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

@Controller
public class IndexController {
    private final WishRepository wishRepository = new WishRepository();
    private final WishListRepository wishListRepository = new WishListRepository();
    private final UserRepository userRepository = new UserRepository();
    private final UserServices userServices = new UserServices();
    private final WishListServices wishListServices = new WishListServices();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/lists")
    public String allLists(Model m, @RequestParam String userID) {
        m.addAttribute("userID", userID);
        m.addAttribute("allWishLists", wishListRepository.getWishListByUserID(userID));
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
        String listName = dataFromForm.getParameter("wishListName");
        String userID = dataFromForm.getParameter("userID");
        boolean doesListExist = wishListServices.doesWishListExist(listName,wishListRepository.getWishListByUserID(userID));
        if(!doesListExist){
        WishList newWishList = new WishList( listName,userID);
        wishListRepository.setActiveWishList(newWishList);
        wishListRepository.saveWishListToDB();
        newWishList.setWishList_ID(wishListRepository.getLastCreatedWishListID());
        newWishList.generateUrl();
        wishListRepository.addToAllWishlists(newWishList);
        wishListRepository.setActiveWishList(newWishList);
        return "redirect:/" + wishListRepository.getActiveWishList().getUrl();}
        else{
            return "redirect:/lists" +"?userID=" + userID;
        }
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
        return "redirect:/all-lists";

    }
    @GetMapping("/deleteWish")
    public String deleteWish(@RequestParam String id){
        wishRepository.deleteWishFromWishlistByWishID(id);

    return "redirect:/" + wishListRepository.getActiveWishList().getUrl();
    }

    @GetMapping("/login")
    public String login(){
        return "sign_in";
    }
    @GetMapping("/login-error")
    public String loginError(){
        return "login-error";
    }

    @PostMapping("/login")
    public String validateLogin(WebRequest userData){
        String username = userData.getParameter("username");
        String password = userData.getParameter("password");
        ArrayList<User> allUsersFromDB= userRepository.getAllUsersFromDB();
        String userExists = userServices.checkUser(username,password,allUsersFromDB);
        if(userExists.equals("")){
            return "redirect:/login-error";
        }
        return "redirect:/lists" +"?userID=" + userExists;
    }

    @GetMapping("/deleteWishList")
    public String deleteWishList(@RequestParam String wishListID, @RequestParam String userID){
        wishListRepository.deleteWishListByID(wishListID);
        return "redirect:/lists" +"?userID=" + userID;
    }


}
