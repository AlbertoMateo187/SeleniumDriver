package nomina;

import java.io.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class word
{
public static void main(String[] args) throws IOException, InvalidFormatException
{
XWPFDocument docx = new XWPFDocument();
XWPFParagraph par = docx.createParagraph();
XWPFRun run = par.createRun();
run.setText("Hello, World. This is my first java generated docx-file. Have fun.");
run.setFontSize(13);
InputStream pic = new FileInputStream("C:\\Users\\Mario\\Downloads\\2021-03-02_09-26-45.jpg");
//byte [] picbytes = IOUtils.toByteArray(pic);
//run.addPicture(picbytes, Document.PICTURE_TYPE_JPEG);
run.addPicture(pic, Document.PICTURE_TYPE_JPEG, "3", 0, 0);
FileOutputStream out = new FileOutputStream("C:\\Users\\Mario\\Downloads\\finallyhurray.doc");
docx.write(out);
out.close();
pic.close();
}
}
