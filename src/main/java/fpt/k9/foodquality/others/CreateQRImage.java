package fpt.k9.foodquality.others;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class CreateQRImage {

	/**
	 * @param args
	 * @throws WriterException
	 * @throws IOException
	 */
//	public static void main(String[] args) throws WriterException, IOException {
//		String qrCodeText = "https://www.journaldev.com";
//		String filePath = "D:\\Pankaj\\JD.png";
//		int size = 125;
//		String fileType = "png";
//		File qrFile = new File(filePath);
//		createQRImage(qrFile, qrCodeText, size, fileType);
//		System.out.println("DONE");
//	}

	public static BufferedImage createQRImage(String qrCodeText, int size) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 2);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
				BarcodeFormat.QR_CODE, size, size, hints);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
				BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		return image;
		//ImageIO.write(image, fileType, qrFile);
	}

}
