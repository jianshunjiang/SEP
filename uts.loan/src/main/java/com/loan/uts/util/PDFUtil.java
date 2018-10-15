package com.loan.uts.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.loan.uts.model.Application;
import com.loan.uts.model.Manager;
import com.loan.uts.model.Student;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PDFUtil {
    public static byte[] contract(Application application, Student student) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] result = null;
        Manager manager = application.getManager();
        Document doc = new Document(PageSize.A4, 30, 30, 70, 30);
        try {
            PdfWriter pw = PdfWriter.getInstance(doc, bos);
            doc.open();
            PdfContentByte cb = pw.getDirectContent();
            //Set header.
            Font ffont = new Font(Font.FontFamily.UNDEFINED, 25, Font.BOLD);
            String contractTitle = "Loan Contact " + application.getId() + " - " + student.getId();
            Phrase header = new Phrase(contractTitle, ffont);
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
            table.setSpacingAfter(10f);
            table.setSpacingAfter(10f);
            int[] widths = {40, 60};
            table.setWidths(widths);
            table.addCell( defaultCell("Contract ID"));
            table.addCell(defaultCell(application.getId() + ""));
            table.addCell(defaultCell("Contract Title"));
            table.addCell(defaultCell(application.getTitle()));
            table.addCell(defaultCell("Student ID"));
            table.addCell(defaultCell(student.getId() + ""));
            table.addCell(defaultCell("Student name"));
            table.addCell(defaultCell(student.getFirstname() + student.getLastname()));
            table.addCell(defaultCell("Student Gender"));
            table.addCell(defaultCell(student.getGender()));
            table.addCell(defaultCell("Student Course"));
            table.addCell(defaultCell(student.getCourse()));
            table.addCell(defaultCell("Student Faculty"));
            table.addCell(defaultCell(student.getFaculty()));
            table.addCell(defaultCell("Student Date of Birth"));
            table.addCell(defaultCell(student.getDob().toString()));
            table.addCell(defaultCell("Student Email"));
            table.addCell(defaultCell(student.getEmail()));
            table.addCell(defaultCell("Student Bank Account"));
            table.addCell(defaultCell(student.getBankaccount()));
            table.addCell(defaultCell("Student Phone"));
            table.addCell(defaultCell(student.getPhone()));
            table.addCell(defaultCell("Amount($)"));
            table.addCell(defaultCell(application.getAmount() + ""));
            table.addCell(defaultCell("Years To Pay Back"));
            table.addCell(defaultCell(application.getPayBackYears() + ""));
            table.addCell(defaultCell("Rate(%/year)"));
            table.addCell(defaultCell((application.getSum()/application.getAmount()-1)/application.getPayBackYears() * 100 + ""));
            table.addCell(defaultCell("Totol Amount To Pay Back($)"));
            table.addCell(defaultCell(application.getSum() + ""));
            table.addCell(defaultCell("Loan Manager ID"));
            table.addCell(defaultCell(manager.getId() + ""));
            table.addCell(defaultCell("Loan Manager Name"));
            table.addCell(defaultCell(manager.getFirstname() + manager.getLastname()));
            table.addCell(defaultCell("Apply date"));
            table.addCell(defaultCell(application.getSubmitDate().toString()));
            table.addCell(defaultCell("Approve date"));
            table.addCell(defaultCell(application.getResultDate().toString()));
            PdfPCell cell = defaultCell("Announcement");
            cell.setColspan(2);
            table.addCell(cell);
            cell = defaultCell("I will use the money properly and pay it back on time.");
            cell.setColspan(2);
            table.addCell(cell);
            table.addCell(defaultCell("Signature"));
            table.addCell(defaultCell(""));
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

    private static PdfPCell defaultCell(String s){
        PdfPCell cell = new PdfPCell(new Paragraph(s));
        cell.setBorderColor(BaseColor.GRAY);
        cell.setPadding(10);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }
}
