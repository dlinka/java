package com.cr.itext;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import static com.cr.common.Facility.print;

public class Demo {

    public static void main(String[] args) throws IOException {
        create();
        fill();
    }

    public static void create() throws FileNotFoundException {
        PdfDocument pdf = new PdfDocument(new PdfWriter("form.pdf"));
        pdf.setDefaultPageSize(PageSize.A4);
        Document doc = new Document(pdf);

        PdfAcroForm form = PdfAcroForm.getAcroForm(doc.getPdfDocument(), true);
        PdfTextFormField name = PdfTextFormField.createText(doc.getPdfDocument(), new Rectangle(99, 753, 425, 15), "name", "name");
        form.addField(name);

        //增加一页
        pdf.addNewPage();

        PdfTextFormField age = PdfTextFormField.createText(doc.getPdfDocument(), new Rectangle(99, 753, 425, 15), "age", "age");
        form.addField(age);

        doc.close();
    }

    public static void fill() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfReader("form.pdf"), new PdfWriter("fill_form.pdf"));
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
        Map<String, PdfFormField> fields = form.getFormFields();
        fields.get("name").setValue("akulaku");
        fields.get("age").setValue("123");
        form.flattenFields();
        pdf.close();
    }


}
