
public class TestHex {

	/**
	 * @param args
	 */
	public static final byte[] decodeHex(String hex)  
    {  
        char chars[] = hex.toCharArray();  
        byte bytes[] = new byte[chars.length / 2];  
        int byteCount = 0;  
        for(int i = 0; i < chars.length; i += 2)  
        {  
            int newByte = 0;  
            newByte |= (byte)(chars[i]);  
            newByte <<= 4;  
            newByte |= (byte)(chars[i + 1]);  
            bytes[byteCount] = (byte)newByte;  
            byteCount++;  
        }  
   
        return bytes;  
    }  
	public static void main(String[] args) {
        System.out.println(decodeHex("123"));
	}

}
