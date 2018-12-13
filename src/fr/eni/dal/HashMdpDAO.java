package fr.eni.dal;

import java.security.MessageDigest;

public class HashMdpDAO {

	public static String hasherMdp(String mdp) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());
        byte byteData[] = md.digest();
        
        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer mdpHash = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	mdpHash.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return mdpHash.toString();
	}
}