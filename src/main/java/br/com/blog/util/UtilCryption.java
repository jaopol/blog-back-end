package br.com.blog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author jp
 *
 */
public class UtilCryption {
	
	/*public static void main(String[] args) {
		
		String cryptionPassword = cryptionPassword("123456");
		System.out.println( cryptionPassword );
		
	}*/
	
	public static String cryptionPassword( String password ) {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder( 16 );
		return bCryptPasswordEncoder.encode( password ) ;
	}
	

}
