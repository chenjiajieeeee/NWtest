package com.cjj.www.test;


public class JdbcUtilTest {
    public static void main(String[] args) {
        String a="123123";
        int result=0;
        int count=0;
        for (int i=a.length()-1;i>=0;i--){
            int i1 = a.charAt(i)-48;
             result+= (int) (Math.pow(10,count++)*i1);
        }
        System.out.println(result);
    }
}
