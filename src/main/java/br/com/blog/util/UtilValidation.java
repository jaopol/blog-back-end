package br.com.blog.util;

public class UtilValidation {

	/**
	 * Verifica se é o mesmo usuário que criou o post para exlcuir.
	 * 
	 * @param user
	 * @param userPost
	 * @return
	 */
	public static boolean isSameUsers( Long idUser, Long idUserAct ) {
		
		if( idUser == idUserAct ) {
			return true;
		}
		
		return false;
	}
	
}
