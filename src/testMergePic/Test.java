package testMergePic;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Test {

	public static boolean merge(String[] pics, String dst_pic, String type) {
		int len = pics.length;
		if (len < 1) {
			System.out.println("pics len < 1");
			return false;
		}
		File[] src = new File[len];
		BufferedImage[] images = new BufferedImage[len];
		int[][] ImageArrays = new int[len][];
		for (int i = 0; i < len; i++) {
			try {
				src[i] = new File(pics[i]);
				images[i] = ImageIO.read(src[i]);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			ImageArrays[i] = new int[width * height];// 从图片中读取RGB
			ImageArrays[i] = images[i].getRGB(0, 0, width, height,
					ImageArrays[i], 0, width);
		}

		int dst_height = 0;
		for (int i = 0; i < images.length; i++)
			dst_height += images[i].getHeight();
		if (dst_height < 1) {
			System.out.println("dst_height < 1");
			return false;
		}

		// 生成新图片
		try {
			int dst_width = images[0].getWidth();
			BufferedImage ImageNew = new BufferedImage(images[0].getWidth(),
					dst_height, BufferedImage.TYPE_3BYTE_BGR);
			int height_i = 0;
			for (int i = 0; i < images.length; i++) {
				ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),
						ImageArrays[i], 0, dst_width);
				height_i += images[i].getHeight();
			}

			File outFile = new File(dst_pic);
			ImageIO.write(ImageNew, type, outFile);// 写图片
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String args[]) {
		merge(new String[] { "D:\\image\\1.gif", "D:\\image\\2.gif" },
				"D:\\image\\3.gif", "gif");
	}

}
