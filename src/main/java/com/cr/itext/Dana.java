package com.cr.itext;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

import static com.cr.common.Facility.print;

@Slf4j
public class Dana {

    public static void main(String[] args) throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfReader("Dana+AFI.pdf"), new PdfWriter("Fill_Dana+AFI.pdf"));
        //获取整个PDF的Form
        PdfAcroForm form = PdfAcroForm.getAcroForm(pdf, true);
        //获取Form的字段
        Map<String, PdfFormField> fields = form.getFormFields();
        if (log.isDebugEnabled()) {
            fields.forEach((k, v) -> {
                print(k + " " + v + " " + v.getFont());
            });
        }

        fields.forEach((k, v) -> {
            try {
                PdfFormField pdfFormField = fields.get(k);
                pdfFormField.setFont(PdfFontFactory.createFont());
                if (k.equals("1_contractNo")) {
                    pdfFormField.setValue("0921-AFI-618987");
                } else if (k.equals("2_contractdate")) {
                    pdfFormField.setValue("08/09/2021");
                } else if (k.equals("3_username")) {
                    pdfFormField.setValue("ZAINAL ABIDIN");
                } else if (k.equals("4_usericNo")) {
                    pdfFormField.setValue("3275081404870023");
                } else if (k.equals("5_userphone")) {
                    pdfFormField.setValue("08128735579");
                } else if (k.equals("6_orderNo")) {
                    pdfFormField.setValue("618987");
                } else if (k.equals("7_orderamount")) {
                    pdfFormField.setValue("970.000");
                } else if (k.equals("8_installmentamount")) {
                    pdfFormField.setValue("970.000");
                } else if (k.equals("9_downpayment")) {
                    pdfFormField.setValue("0");
                } else if (k.equals("10_adminfee")) {
                    pdfFormField.setValue("48.239");
                } else if (k.equals("11_monthlyrate")) {
                    pdfFormField.setValue("3.49");
                } else if (k.equals("12_firstduedate")) {
                    pdfFormField.setValue("02/04/2021");
                } else if (k.equals("13_lastduedate")) {
                    pdfFormField.setValue("02/06/2021");
                } else if (k.equals("14_period")) {
                    pdfFormField.setValue("3");
                } else if (k.equals("15_monthlyrepayamount")) {
                    pdfFormField.setValue("186.167");
                } else if (k.equals("16_dueday")) {
                    pdfFormField.setValue("2");
                } else if (k.equals("17_signdate")) {
                    pdfFormField.setValue("09/03/2021");
                } else if (k.equals("18_signdate")) {
                    pdfFormField.setValue("09/03/2021");
                } else if (k.equals("19_username")) {
                    pdfFormField.setValue("ZAINAL ABIDIN");
                } else if (k.equals("20_orderNo")) {
                    pdfFormField.setValue("618987");
                } else if (k.equals("21_orderNo")) {
                    pdfFormField.setValue("618987");
                } else if (k.equals("22_goodstype")) {
                    pdfFormField.setValue("top-up");
                } else if (k.equals("23_downpayment")) {
                    pdfFormField.setValue("0");
                } else if (k.equals("24_installmentamount")) {
                    pdfFormField.setValue("970.000");
                } else if (k.equals("25_monthlyrate")) {
                    pdfFormField.setValue("3.49");
                } else if (k.equals("26_period")) {
                    pdfFormField.setValue("3");
                } else if (k.equals("27_adminfee")) {
                    pdfFormField.setValue("48.239");
                }
                //取消边框
                pdfFormField.setBorderWidth(0);
            } catch (IOException e) {
                print(e);
            }
        });
        //去掉form
        form.flattenFields();
        pdf.close();
    }

}
