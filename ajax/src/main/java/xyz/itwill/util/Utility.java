package xyz.itwill.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
	public static String encrypt(String pw) {
		String encryptPassword="";
		
		try {			
			MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
			
			messageDigest.update(pw.getBytes());			
			
			byte[] digest=messageDigest.digest();
			
			for(int i=0;i<digest.length;i++) {
				encryptPassword+=Integer.toHexString(digest[i] & 0xff);
			}
		} catch (NoSuchAlgorithmException e) { 
			System.out.println("[에러]잘못된 암호화 알고리즘을 사용 하였습니다.");
		}
		return encryptPassword;
	}

	public static String stripTag(String source) {
		Pattern htmlTag=Pattern.compile("\\<.*?\\>");
				
		Matcher matcher=htmlTag.matcher(source);
				
		return matcher.replaceAll("");
	}	
	
	public static String escapeTag(String source) {
		return source.replace("<", "&lt;").replace(">", "&gt;");
	}
}