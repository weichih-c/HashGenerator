import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Converter {
	
	public static String md5Hash (String filePath) throws IOException {
		File file = new File(filePath);
		
		if(!file.exists())
			throw new IOException("File doesn't exists.");
		FileInputStream fis = null;
		DigestInputStream dis = null;
		byte[] buff = new byte[1024];

		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			dis = new DigestInputStream(fis, md);

			while(dis.read(buff) != -1);
			byte[] md5Digest = md.digest();

			return ByteToHex.bytesToHex( md5Digest );	// return the md5 hex string.

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		} finally {
			buff = null;	// closing the input stream.
			if(fis != null) fis.close();
			if(dis != null) dis.close();
		}

		return null;	// if null then some exception should be handle.
	}
}
