package com.loan.uts.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
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

            String header = "Loan Contact" + application.getId() + " - " + student.getId();
            doc.add(new Paragraph(header));
            PdfPTable table = new PdfPTable(2);
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
