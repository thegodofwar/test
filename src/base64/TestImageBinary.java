package base64;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TestImageBinary {
	static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
	static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	public static void main(String[] args) {
		System.out.println(getImageBinary()); // 将图片转成base64编码
		//base64StringToImage(getImageBinary()); // 将base64的编码转成图片
		base64StringToImage("/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0a"+
"HBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIy"+
"MjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABgAGQDASIA"+
"AhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQA"+
"AAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3"+
"ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWm"+
"p6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4 Tl5ufo6erx8vP09fb3 Pn6/8QAHwEA"+
"AwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSEx"+
"BhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElK"+
"U1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3"+
"uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3 Pn6/9oADAMBAAIRAxEAPwD3 iii"+
"gAoorL1XWbTR4vMuTKzHG2OGMu7EkKAAPUkAZ7mgCXUdTtNLtvOupAoJ2ogG55GPRVUcsT6CsxJ/"+
"EeqBmijt9HtzzGZ08 cj/aQEKn5tWNcQw6lczPdrbieE7r2/lAdbP 7BC3Z eSPx5IAb9mv4Qbm1"+
"13VW2jMS3NnKUc8EA 3GOR3 hoA6FdM1vB3 ICT222cY/PrUJbxPYxiV307UEBy6KjwOFz1BywJx"+
"2wOaPD vfboEhvmMd6ZHRUaJkLAcjqAN23BIFdFigDM0jWrPWrd5bZiGjfZLE A8bejCtOud1uB9"+
"LlTXrRGY2yBLqFAAHt8ksQAMllyWAz6jvW5BNFcwRzwyLJFIodHU5DA8gigCaiiigAooooAKKK5n"+
"xFqGrwXUFppUYeSSCSXaoBdtpUYGSAOGJBJwSAOM0AdISFBJIAHUmvOWubrV7iTxBJK1vp9zKtna"+
"rGpad1DMo8rkAMxJO45wM4xjJsXelSXGi6VaPqd/NfahcpGZ5ZXjk8kHfICgO0HapU8Yy30rql0O"+
"1S9spgCI7GMpbQDhEJGC2O7YyAewJ9aAMnZDpdg2oarGmm6NpkZlhtdwbbt/5aORkM3ooJ5OeSRj"+
"G8L/APCUeMLZ9cv9Xu9I0y5ctp9haxRiQRdFeR2ViSRzj8av63b6RptpNqXi6C2vIpiRLJKnmRxD"+
"IEcUcZyWJ9hknJOOBUFrqF3qKxnTfBeqLbOMh9SvBbIF6cJuZhx0G0fhQB0troFtbXy30kk91eJH"+
"5az3DAsB3xgAZPritiuTHh29uL6yuZWt7VLchisMskjDBzgM2MZ6E9xXRXV9BZCEzuEE0qwp7u3Q"+
"UATOgkjZTnDDBwcH8xXD GNPuLzTLmE6jqaR2V1NbRolyqrKqsdvIXcvBA6g8ZrRXVL7Urqd9GvY"+
"JrIyMrXDxERwbRtIDfxnOT6DA57Vj FdKvX1C/u7W/BjWQfvJ43kDylfnZRvUY oJJOcjoACtPfe"+
"KvC mL56TXQZuACJPIU9S8jZLEYLbVHsDXfaZeRahpdtdwzpOk0auJUGFbjqAen0rD8US6ZYTWuo"+
"6hqV1bPGVWOOAk7/AJuflHUEHB9vpV3wzbPDpxcQPb20pVre3c5ZE2gAt6McZI7Z9c0AbtFFFABW"+
"B4hS6tUTWLCSNZbRWEqSDKyQkguOowwxke4x3rfrK1f/AElrfTFPNw4aUDqIl5Y/icL/AMCoAr6c"+
"jajqr6tJGywJH5NlvBBKthnkweRuIUDPOF963a5/WNcTTtb0nTnu44WvnYKvlGR5CMfKMfdGNxLH"+
"AMtwT804B4O0DAyCAGz3r0AWmqw2hji1OOWXDYluLfLZJJH3SBx0xis/S/BOjaT4ah0K2hdbeJSB"+
"Nn99knLNvHIYnuMdsdKnOiX8UzJZ61dwW2wlQ5E7bz7yAnaB2zk568UATyWGq3dpHDcX0ETqyszw"+
"RNlsdRy3ANQQ F7U2ltb6lcT6lHbbfKW4I2AqMA7VABPuc9TSNpPiELGyeJTuX74ayj2v0/Ed 9Q"+
"HwvdXnGq6vcXMbNueJMoregxnAHtj8aAHanfz3FrLp3h 0EzIhjeYbRDCOm1c8M OijgdyOhv2E0"+
"Gm6TZpdCOxB2xJHJJklieBk9WP55zUiNYaDp 2a8jhtogzKZpABGg7AnsK5iC tviDPOLCbztCiZ"+
"FNztXDyKdx8sHkHlQWI7HHWgB0it4g8TXGny61JcWUMm82tvEgEbIeVkYjcMNjBHXB9DXcVQ07S4"+
"dNSXy3kklmk8yWWQgs7YAycADoAOAKv0AFFFFAEUsscETyyuqRopZmY4CgckmsvREkuDNq1wCr3m"+
"3yUZcGKAD5FPuclj7tjtUGr7tU1W20VAfs4UXV6exjB SP8A4Ew5/wBlGHet1mCKWYgADJJ7CgDm"+
"9W0 C41yIELJeXcfkqwXDQWynMuG6jcSFz7j0p/jLw6PEfhG80uFUS4Me61YjhJF5X6DjH0NO8Oh"+
"r S816UEG9YJbg/wW6ZCfTcSzH/eHpXRUAfMWk/GPxZ4dvbCw8Rxu1tBGiuPLCztHkc5JwxwuMn1"+
"PevYNJ MXgjVrbzRrUdmw6xXimNh/Q/gTWH8UPhxputfZdXMhtkhmxclYwVRHPMhHBIDEFhnoWPB"+
"rntY/Z0guCk2j6v9mLkF4Z0LonHO1hyRnpnt3oA69fjf4JEsgl1FlULuRlhd93HI4HBz BqaLxT4"+
"116J7jQvDUUWm3RRbS61CcRuqN1mMQ5ZcEEDIP1rgfB/ws0S08UeIdF8QWl1cC3EAsLto3TeWHzO"+
"mPlIDFeuQO/evWJfC0lrpVvbaZc/ZWjILpFJJFE/IzhVbCA88DjntQB4p8SPCmuzalpenPr0 qae"+
"LmO1y8aoEuZCzPsVeuF Ykk4zgmvc/BGijw/4QsNOUKFRCw2rt 8S3I9eeax7Hwpbz6xb2k0rz2W"+
"kLJhWGA002CV78LHgdcneck857scCgAooooAKKKKAGhQCWAGT1NYPieaWS3h0m3JE2oP5RI/hj/i"+
"P5f1roO1c3ow/tbXLvWm/wBTGPstqCP4Qcs34mgDfghS3gjhiGI41CKPQAYFS01mVVLMQFHUk9Kh"+
"muobe3knllVY487mz0/ v7UAPlhSeJ4pUV43UqysMgg9Qa4yDxTZ6Jr0nhDVdRW3uPKD6fdSEfPG"+
"2QqMTx5i4xz94YPXNQ6j4w1q2v4hDpsZtpmdY48M8p2EKxIU5zubGFBxg5pt54NHiuO9HiizCnU4"+
"Y8RQtuNk8W7aQ/diJCT24xyKANmeLV4Zlsol1GeAupa/ 0wKwHf5dvQfSma/4nt/Ddtb2PnPe6tc"+
"EJBCcFzlgvmSbQNqAkZbA9BzXjOtal8Tfhe50OC7k1DTZjssLx4PNIz0Ck5KsP7pyPTiu40Xwjda"+
"b4ahgvJZZvFetOtzd3Ez7mHlESeWW7LkKvHdqAPSNMsRp9ksJkMkhYySykYMkjHLN7ZJ6dhgVoVx"+
"KeMbyPV4INRtIbWNziWHLGWAdnYkBSue4yK7CKeKdN8To6ZI3KQRkHBoAmoqna6jZX0MM1rcxSpN"+
"kxlW /jg4 lXKACiiigCpqF2LOwluCAxRflX 8x4UfiSB NYNvcH7fF4ftb6KGS3hE12ykNKxYnI"+
"UHpk7iTg4yMdci/rtnJqRsrJZbiKJ5TJLLAdrAKOBu/hySORz6U618N6PZTwT2thDDNACI5EGGwR"+
"g5P8WffNAFXXdDtJ9DvYy0jzNA4iNxdOV34 XOWx1xXKaPYWEVvFPHbo/nLb3cKuOFdSjHaT6kuM"+
"kZ Su6kis9ds45A7PEH3I65UqwJB6/iMEVx1jbTXGjRp5c26IPtOz5vlZlbt13IxH/XQUAEV/eWG"+
"uWPBuJpbmWHYSI9 4Zz06lVQ hINdpBqiSSeVNBcWsnQCZPlP0YZU/nXCX6TtcRXckU261uo7ktG"+
"nzAI ZFBx02uWHqIyO9ehTwW2o2UkEypNbzKUdTyGB4IoAo69pratp32IBdkkqeYxbBVAckj1OBg"+
"fX2rkZvFIX4nx2P2v7LpGn2zwzb8qjSkAjLEY4GABnrmumitb7T9JNk9zLdgssEEo SZEY4yzcgl"+
"R/FjnHIzV7TtHstMiCwQ7n6tNKd8jnuWc8k0AY r31pqCW81lHLPPDOgRvKdY2DMoKlyACDkHGeo"+
"Fcs1pb2cctjYM0Frc3O2aJXPlniZ5FAzwCI1PGMbj24rt9enSP7Ijq5RJPtL7Rn5Y8ED6lig/GsR"+
"dPMeg2moy2gkaKeS4kjfPzrKrIx6ZGA3/fIoAg0rwyYtbubvSZEsY7ItDbQzRCTliWcsM5CnI2kE"+
"HBPrz0lpqlzHdJZarbrbXEgPlSRvvilIGSFJAIbqdpHTpnBrlPDMk8 qSYgvIJLyNSb1VZgTGowp"+
"JG3GD7ZOfUgdVeaVcalb TdXKAAbk2R8pKDlJA3YjigDaoqjplzNd6dDNMnlTEFZEYdHBIbHtkHH"+
"tRQB/9k=");
	}

	static String getImageBinary() {
		File f = new File("d://1.jpg");
		BufferedImage bi;
		try {
			bi = ImageIO.read(f);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpg", baos);
			byte[] bytes = baos.toByteArray();

			return encoder.encodeBuffer(bytes).trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	static void base64StringToImage(String base64String) {
		try {
			byte[] bytes1 = decoder.decodeBuffer(base64String);
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
			BufferedImage bi1 = ImageIO.read(bais);
			File w2 = new File("d://3.png");// 可以是jpg,png,gif格式
			ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}