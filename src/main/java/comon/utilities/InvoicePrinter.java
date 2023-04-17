/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comon.utilities;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import dto.hoadon.HoaDonChiTietDTO;
import dto.hoadon.HoaDonDTO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Normalizer;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import service.hoadon.HoaDonChiTietService;
import service.hoadon.impl.HoaDonChiTietServiceImpl;

/**
 *
 * @author Admin
 */
public class InvoicePrinter {

    /**
     *
     */
    public static final HoaDonChiTietService hoaDonChiTietService = new HoaDonChiTietServiceImpl();

    public static String utf8Remove(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        temp = temp.replaceAll("[đĐ]", "D");
        return temp;
    }

    public static void printInvoice(HoaDonDTO hoaDonDTO) throws IOException {
        String fontPath = "C:\\Users\\Admin\\Documents\\GitHub\\NDT_Shop\\src\\main\\resources\\fonts\\arialuni.ttf";
        PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, true);

        try {
            String path = "D:\\" + hoaDonDTO.getMaHD() + ".pdf";

            PdfWriter pdfWriter = new PdfWriter(path);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDocument);
            pdfDocument.setDefaultPageSize(PageSize.A4);
            float col = 280f;
            float columnWitdh[] = {col, col};
            Table table = new Table(columnWitdh);
            table.setFixedLayout();
            table.setHeight(150f);

            table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
            table.addCell(new Cell().add("HOA DON")
                    .setFont(font)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setFontSize(30f)
                    .setBorder(Border.NO_BORDER)
            );
            table.addCell(new Cell().add(
                    "NDT SHOP"
                    + "\n#" + hoaDonDTO.getKhachHang().getMaKH() + " " + utf8Remove(hoaDonDTO.getKhachHang().getTen())
                    + "\n" + hoaDonDTO.getId().toUpperCase())
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setVerticalAlignment(VerticalAlignment.MIDDLE)
                    .setMarginTop(30f)
                    .setMarginBottom(30f)
                    .setMarginRight(10f)
                    .setBorder(Border.NO_BORDER)
            ).setFont(font);

            float colWidth[] = {80, 300, 100, 80};
            Table customerInforTable = new Table(colWidth);

            customerInforTable.addCell(new Cell(0, 4)
                    .add("THONG TIN KHACH HANG")
                    .setFont(font)
                    .setBold()
                    .setBorder(Border.NO_BORDER)
            );

            customerInforTable.addCell(new Cell().add("HO TEN").setBorder(Border.NO_BORDER)).setFont(font);
            customerInforTable.addCell(new Cell().add(utf8Remove(hoaDonDTO.getKhachHang().getTen())).setBorder(Border.NO_BORDER).setFont(font));
            customerInforTable.addCell(new Cell().add("Ma HD").setBorder(Border.NO_BORDER).setFont(font));
            customerInforTable.addCell(new Cell().add(hoaDonDTO.getMaHD()).setBorder(Border.NO_BORDER).setFont(font));

            customerInforTable.addCell(new Cell().add("ID").setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add(hoaDonDTO.getId().toUpperCase()).setBorder(Border.NO_BORDER));
            customerInforTable.addCell(new Cell().add("Ngay").setBorder(Border.NO_BORDER).setFont(font));
            customerInforTable.addCell(new Cell().add(DateTimeUtil.formatDate(new Date(hoaDonDTO.getCreatedAt()))).setBorder(Border.NO_BORDER));

            float itemInforColWidth[] = {112, 112, 112, 112, 112};
            Table itemInforTable = new Table(itemInforColWidth);

            itemInforTable.addCell(new Cell().add("STT")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
            );
            itemInforTable.addCell(new Cell().add("TEN SP")
                    .setFont(font)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
            );
            itemInforTable.addCell(new Cell().add("SO LUONG")
                    .setFont(font)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT)
            );
            itemInforTable.addCell(new Cell().add("DON GIA")
                    .setFont(font)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT)
            );
            itemInforTable.addCell(new Cell().add("THANH TIEN")
                    .setFont(font)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT)
            );

            List<HoaDonChiTietDTO> listGioHang = hoaDonChiTietService.findByHoaDon(hoaDonDTO.getId());
            int index = 1;
            float tongTien = 0;

            for (HoaDonChiTietDTO hoaDonChiTietDTO : listGioHang) {
                String tenSP = hoaDonChiTietDTO.getSanPham().getTenSP();
                String donGia = VndConvertUtil.floatToVnd(hoaDonChiTietDTO.getDonGia());
                String soLuong = hoaDonChiTietDTO.getSoLuong() + "";
                String thanhTien = VndConvertUtil.floatToVnd(hoaDonChiTietDTO.getDonGia() * hoaDonChiTietDTO.getSoLuong());

                itemInforTable.addCell(new Cell().add(index + ""));
                itemInforTable.addCell(new Cell().add(utf8Remove(tenSP)).setFont(font));
                itemInforTable.addCell(new Cell().add(utf8Remove(soLuong)).setTextAlignment(TextAlignment.RIGHT));
                itemInforTable.addCell(new Cell().add(donGia).setTextAlignment(TextAlignment.RIGHT));
                itemInforTable.addCell(new Cell().add(thanhTien).setTextAlignment(TextAlignment.RIGHT));
                index++;
                tongTien += VndConvertUtil.vndToFloat(thanhTien);
            }

            itemInforTable.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setBorder(Border.NO_BORDER)
            );
            itemInforTable.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setBorder(Border.NO_BORDER)
            );
            itemInforTable.addCell(new Cell().add("")
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setBorder(Border.NO_BORDER)
            );
            itemInforTable.addCell(new Cell().add("TONG TIEN")
                    .setFont(font)
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT)
            );
            itemInforTable.addCell(new Cell().add(VndConvertUtil.floatToVnd(tongTien))
                    .setBackgroundColor(new DeviceRgb(63, 169, 219))
                    .setBorder(Border.NO_BORDER)
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.RIGHT)
            );

            document.add(table);
            document.add(new Paragraph("\n"));
            document.add(customerInforTable);
            document.add(new Paragraph("\n"));
            document.add(itemInforTable);
            document.add(new Paragraph("\n(Nguoi dai dien ky duyet)").setTextAlignment(TextAlignment.RIGHT)).setFont(font);

            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(path);
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            document.close();
        } catch (FileNotFoundException e) {
        }

    }

}
