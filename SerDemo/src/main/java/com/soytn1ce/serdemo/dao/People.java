package com.soytn1ce.serdemo.dao;

public class People {
    private String name;
    public void getName(){
        System.out.println(this.name);
    }
    private class Nopublic{
        private String str = "private inner class";
        public Nopublic(String s){
            System.out.println(s+ str);
        }
    }
}
