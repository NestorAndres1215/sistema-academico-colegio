package com.colegio.backend.shared.report.pdf;


import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.draw.LineSeparator;
import org.springframework.stereotype.Component;

import static com.colegio.backend.shared.report.pdf.PdfStyle.*;
@Component
public class PdfElements {

    // Crea una tabla PDF con el formato y proporciones establecidas.
    public PdfPTable createStyledTable(float[] widths) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(widths);
        table.setSpacingAfter(10);

        return table;
    }

    // Crea un párrafo centrado con un espaciado superior.
    public Paragraph spaced(String text, Font font) {
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(3);
        return p;
    }

    // Crea una celda para mostrar los datos de una firma.
    public PdfPCell createSignatureCell(String title, String name, String dni, Font normalFont, Font boldFont) {

        PdfPCell cell = new PdfPCell();

        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingTop(30);

        Font titleFont = new Font(boldFont.getFamily(), 10, Font.BOLD, COLOR_PRINCIPAL);

        Paragraph line = new Paragraph("_______________________________",
                new Font(normalFont.getFamily(), 10, Font.NORMAL, COLOR_DORADO));

        line.setAlignment(Element.ALIGN_CENTER);

        cell.addElement(line);
        cell.addElement(spaced(title, titleFont));
        cell.addElement(spaced(name, normalFont));
        cell.addElement(spaced(dni, normalFont));
        cell.addElement(spaced("Firma", new Font(normalFont.getFamily(), 9, Font.ITALIC, COLOR_TEXTO_SECUNDARIO)));

        return cell;
    }


    // Agrega una fila con etiqueta y valor a una tabla PDF.
    public void addRow(
            PdfPTable table,
            String label,
            String value,
            Font labelFont,
            Font valueFont,
            boolean shaded
    ) {

        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBackgroundColor(COLOR_PRINCIPAL);
        labelCell.setBorderColor(COLOR_BORDE_TABLA);
        labelCell.setPadding(7);
        labelCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell valueCell = new PdfPCell(new Phrase(value != null ? value : "-", valueFont));
        valueCell.setBackgroundColor(shaded ? COLOR_FILA_PAR : BLANCO);
        valueCell.setBorderColor(COLOR_BORDE_TABLA);
        valueCell.setPadding(7);
        valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    // Agrega un párrafo de texto justificado al documento.
    public void addParagraph(Document document, String text, Font font) throws DocumentException {

        Paragraph paragraph = new Paragraph(text, font);

        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

        paragraph.setLeading(14);
        paragraph.setSpacingAfter(6);

        document.add(paragraph);
    }

    // Agrega una línea decorativa dorada al documento.
    public void addGoldRule(Document document) throws DocumentException {

        LineSeparator line = new LineSeparator(1.2f, 100f, COLOR_DORADO, Element.ALIGN_CENTER, -2);

        Paragraph rule = new Paragraph();
        rule.add(new Chunk(line));
        rule.setSpacingAfter(10);

        document.add(rule);
    }

    // Agrega un título de sección al documento PDF.
    public void addSection(Document document, String title, Font font) throws DocumentException {

        Paragraph paragraph = new Paragraph(title, font);

        paragraph.setSpacingBefore(12);
        paragraph.setSpacingAfter(6);

        document.add(paragraph);
    }

    // Crea un párrafo alineado a la derecha.
    public Paragraph createRightAlignedParagraph(String text, Font font) {
        Paragraph paragraph = new Paragraph(text, font);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        return paragraph;
    }

    // Crea un título centrado para el documento.
    public Paragraph createCenteredTitle(String text, Font font) {
        Paragraph title = new Paragraph(text, font);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(2);
        return title;
    }

    // Crea una referencia centrada para el documento.
    public Paragraph createCenteredReference(String text, Font font) {
        Paragraph reference = new Paragraph(text, font);
        reference.setAlignment(Element.ALIGN_CENTER);
        reference.setSpacingAfter(4);
        return reference;
    }

}
