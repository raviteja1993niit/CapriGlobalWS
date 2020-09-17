package com.cgcl;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     String first="def";
     String sec="abc";
     String third = null;
     if(first !=null && first != "")
    	 third=first+"^;"+sec;
     else 
    	 third=sec;
     System.out.println(third);
	}

}
