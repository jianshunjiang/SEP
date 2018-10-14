package com.loan.uts.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.loan.uts.model.Application;
import com.loan.uts.model.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFUtil {
    public static byte[] contract(Application application, Student student) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] result = null;

        Document doc = new Document();
        try {
            PdfWriter pw = PdfWriter.getInstance(doc, bos);


            doc.open();
            PdfContentByte cb = pw.getDirectContent();
            //Set header.
            Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.ITALIC);
            Phrase header = new Phrase("Loan Contact " + application.getId() + " - " + student.getId(), ffont);
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER,
                    header,
                    (doc.right() - doc.left()) / 2 + doc.leftMargin(),
                    doc.top() + 10, 0);
//            doc.add(new Paragraph(header));
            //Initialize the style of the table;
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.getDefaultCell().setUseAscender(true);
            table.getDefaultCell().setUseDescender(true);
            table.addCell("Student name");
            table.addCell(student.getFirstname() + student.getLastname());
            table.addCell("Signature");
            table.addCell("");

            doc.add(table);
            doc.close(); //Document should be closed before getting the bytes.
            result = bos.toByteArray();
            bos.close();
            pw.close();

        } catch (DocumentException e) {

            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
