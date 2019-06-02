package com.example.userLogin.userRegistration.controller;

import com.example.userLogin.userRegistration.config.HibernateUtil;
import com.example.userLogin.userRegistration.dao.userDao;
import com.example.userLogin.userRegistration.model.UserModel;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class FormController {

    @GetMapping("/home")
    public String homePage() { return "home";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute UserModel user) { return "login";
    }


    @GetMapping("/confirm")
    public String displayLogin(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "confirm";
    }

    @GetMapping("/logout")
    public String logOut() {

        return "logout";
    }

    @GetMapping("/update")
    public String update(@ModelAttribute UserModel user) {

        return "accountUpdate";
    }
    @PostMapping("/login")
    public String handleForm(@ModelAttribute UserModel user)
    {
        String result="confirm";
        Boolean condition1= user.getName()!=null
                && user.getPassword()!=null && user.getEmail()!=null;
        Boolean condition2=user.getUserId()!=0&& user.getName()!="" &&user.getAge()!=0
                && user.getPassword()!="" && user.getEmail()!="";
        if(condition1&& condition2) {
            result="login";
            // insert into H2 DB
            SessionFactory sf = HibernateUtil.getSessionFactory();
            Session session=sf.openSession();
            userDao userDaoObj=  new userDao(session);
            userDaoObj.insertUser(user);


        }

        return result;
    }

    @PostMapping("/admin")
    public String handleAfterLogging(@ModelAttribute UserModel user)
    {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();
        // get the values


        userDao userDao = new userDao(session);

        userDao.getUserById(user.getUserId());
        return "accountUpdate";
    }

    @PostMapping("/update")
    public String updatePassword(@ModelAttribute UserModel user)
    {
        //update password

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session=sf.openSession();


        userDao userDao = new userDao(session);

       // userDao.getUserById(user.getUserId());
        userDao.updatePassword(user);


        return "Updated";
    }





}
