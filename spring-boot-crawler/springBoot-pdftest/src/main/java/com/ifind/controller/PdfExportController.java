package com.ifind.controller;

import com.ifind.entity.Product;
import com.ifind.util.PdfExportUtil;
import com.ifind.util.PdfUtil;
import com.ifind.util.ViewPDF;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pdf")
public class PdfExportController {

    @RequestMapping("/export")
    public ModelAndView printPdf(){
        Product product1 = new Product("产品一","cp01",120);
        Product product2 = new Product("产品一","cp01",120);
        Product product3 = new Product("产品一","cp01",120);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        Map<String, Object> model = new HashMap<>();
        model.put("sheet", products);
        return new ModelAndView(new ViewPDF(), model);
    }

    @RequestMapping("/load")
    public void exportPdf(HttpServletRequest request, HttpServletResponse response){

        Product product1 = new Product("产品一","cp01",120);
        Product product2 = new Product("产品二","cp02",120);
        Product product3 = new Product("产品三","cp03",120);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        PdfExportUtil.exportPdf("test", products, request, response);
    }
}
