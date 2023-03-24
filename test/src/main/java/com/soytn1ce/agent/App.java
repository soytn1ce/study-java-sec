package com.soytn1ce.agent;

import com.soytn1ce.agent.dao.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        User user = new User();
        user.setAge(1);
        user.setName("mike");
        String s = user.toString();
        System.out.println(s);
        System.out.println( "User's name is:" + user.getName() + "!" );
    }
}
