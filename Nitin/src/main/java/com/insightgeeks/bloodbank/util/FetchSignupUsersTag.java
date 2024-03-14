//package com.insightgeeks.bloodbank.util;
//
//import com.insightgeeks.bloodbank.entities.UserModel;
//import com.insightgeeks.bloodbank.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.jsp.JspException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.tagext.TagSupport;
//
//public class FetchSignupUsersTag extends TagSupport {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Override
//    public int doStartTag() throws JspException
//    {
//        Iterable<UserModel> users = userRepository.findAll();
//        JspWriter out = pageContext.getOut();
//        try {
//            for(UserModel user:users)
//            {
//                if(user.getRole().equalsIgnoreCase("user"))
//                {
//                    out.println(user);
//                }
//            }
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return SKIP_BODY;
//    }
//}
