/**
 * 
 */
package com.sivalabs.blog;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Siva
 *
 */
public class TestUtil
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("admin: "+encoder.encode("admin"));
		System.out.println("siva: "+encoder.encode("siva"));
		
	}

}
