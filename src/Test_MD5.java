import java.security.MessageDigest;

public class Test_MD5 {
	public final static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 使用MD5创建MessageDigest对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			System.out.println("mdTemp=" + mdTemp);
			byte[] md = mdTemp.digest();
			System.out.println(new String(md));
			System.out.println(new String(md).length());
			int j = md.length;
			System.out.println("j=" + j);
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte b = md[i];
				System.out.println((int) b);
				// 将每个数(int)b进行双字节加密
				str[k++] = hexDigits[b >> 4 & 0xf];
				str[k++] = hexDigits[b & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 测试
	public static void main(String[] args) {
		System.out.println("caidao的MD5加密后：\n" + Test_MD5.MD5("caidao"));
		System.out.println("");
	}
}
