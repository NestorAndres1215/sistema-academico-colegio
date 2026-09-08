package com.colegio.backend.modules.teacher.application.service;

import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.colegio.backend.modules.companies.domain.port.usecase.CompanyUseCase;
import com.colegio.backend.modules.teacher.application.dto.teacher_contract.TeacherContractReportResponse;
import com.colegio.backend.modules.teacher.application.mapper.TeacherContractReportMapper;
import com.colegio.backend.modules.teacher.domain.model.TeacherContract;
import com.colegio.backend.modules.teacher.domain.port.repository.TeacherContractRepositoryPort;
import com.colegio.backend.modules.teacher.domain.port.usecase.TeacherContractReportUseCase;
import com.colegio.backend.shared.exception.BadRequestException;
import com.colegio.backend.shared.exception.NotFoundException;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import com.lowagie.text.pdf.draw.LineSeparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeacherContractReportService implements TeacherContractReportUseCase {

    private final TeacherContractRepositoryPort teacherContractRepositoryPort;
    private final TeacherContractReportMapper teacherContractReportMapper;
    private final CompanyUseCase companyUseCase;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final String SUBTITULO_EMPRESA = "Sistema de Gestión Educativa";

    // ---- Paleta de colores institucional ----
    private static final Color COLOR_PRINCIPAL = new Color(16, 38, 74);
    private static final Color COLOR_PRINCIPAL_OSCURO = new Color(10, 26, 53);
    private static final Color COLOR_DORADO = new Color(196, 160, 75);
    private static final Color COLOR_DORADO_SUAVE = new Color(224, 199, 138);
    private static final Color COLOR_TEXTO_SECUNDARIO = new Color(115, 126, 148);
    private static final Color COLOR_FILA_PAR = new Color(244, 246, 250);
    private static final Color COLOR_BORDE = new Color(224, 229, 238);
    private static final Color COLOR_BORDE_TABLA = new Color(206, 214, 228);
    private static final Color BLANCO = Color.WHITE;
    private static final Color GRIS_OSCURO = new Color(38, 42, 54);

    private static final float BAND_HEIGHT = 100f;
    private static final float FOOTER_HEIGHT = 50f;
    private static final float PAGE_WIDTH = PageSize.A4.getWidth();
    private static final float PAGE_HEIGHT = PageSize.A4.getHeight();

    private CompanyResponse getCompany() {
        return companyUseCase.findById(1L);
    }

    @Override
    public byte[] generateContractPdf(Long contractId) {

        TeacherContract contract = findById(contractId);

        TeacherContractReportResponse response = teacherContractReportMapper.toResponse(contract);

        return generatePdf(response);
    }

    private byte[] generatePdf(TeacherContractReportResponse contract) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        CompanyResponse company = getCompany();


        Document document = new Document(
                PageSize.A4,
                50,
                50,
                BAND_HEIGHT + 15,
                FOOTER_HEIGHT + 25
        );

        try {

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new HeaderFooterWatermarkEvent(company));

            document.open();

            Font sectionFont = new Font(Font.HELVETICA, 11, Font.BOLD, COLOR_PRINCIPAL);
            Font normalFont = new Font(Font.HELVETICA, 10, Font.NORMAL, GRIS_OSCURO);
            Font boldFont = new Font(Font.HELVETICA, 10, Font.BOLD, COLOR_PRINCIPAL_OSCURO);
            Font titleFont = new Font(Font.HELVETICA, 15, Font.BOLD, COLOR_PRINCIPAL);
            Font italicFont = new Font(Font.HELVETICA, 9, Font.ITALIC, COLOR_TEXTO_SECUNDARIO);

            Paragraph title = new Paragraph(
                    "CONTRATO DE PRESTACIÓN DE SERVICIOS DOCENTES",
                    titleFont
            );

            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(2);

            document.add(title);

            Paragraph contractRef = new Paragraph("N.° de contrato: " + contract.id(), italicFont);
            contractRef.setAlignment(Element.ALIGN_CENTER);
            contractRef.setSpacingAfter(4);
            document.add(contractRef);

            addGoldRule(document);

            addParagraph(
                    document,
                    "Conste por el presente documento el Contrato de Prestación de "
                            + "Servicios Docentes que celebran, de una parte, "
                            + safe(company.name(), "LA INSTITUCIÓN EDUCATIVA")
                            + (isBlank(company.taxId()) ? "" : ", con RUC N.° " + company.taxId())
                            + (isBlank(company.address()) ? "" : ", con domicilio en " + company.address())
                            + ", en adelante EL COLEGIO; y de otra parte, "
                            + getFullName(contract)
                            + ", identificado(a) con DNI N.° " + safe(contract.dni(), "-")
                            + ", con especialidad en " + safe(contract.specialty(), "-")
                            + ", en adelante EL DOCENTE; quienes acuerdan celebrar el presente "
                            + "contrato bajo los términos y condiciones establecidos en las "
                            + "siguientes cláusulas:",
                    normalFont
            );


            addSummaryTable(document, contract);

            addSection(document, "PRIMERA: OBJETO DEL CONTRATO", sectionFont);

            addParagraph(
                    document,
                    "El presente contrato tiene por objeto establecer los términos y "
                            + "condiciones bajo los cuales EL DOCENTE prestará servicios de "
                            + "enseñanza en " + safe(company.name(), "la institución educativa")
                            + ", desarrollando las actividades académicas correspondientes a "
                            + "su especialidad en " + safe(contract.specialty(), "la materia asignada") + ".",
                    normalFont
            );

            addSection(document, "SEGUNDA: PLAZO Y VIGENCIA", sectionFont);

            addParagraph(
                    document,
                    "El presente contrato se celebra bajo la modalidad de "
                            + safe(contract.contractType(), "-").toLowerCase()
                            + " y tendrá vigencia desde el " + formatDate(contract.startDate())
                            + " hasta el " + formatDate(contract.endDate())
                            + ", fecha en la cual concluirá automáticamente sin necesidad de "
                            + "aviso previo, salvo que las partes acuerden su renovación.",
                    normalFont
            );

            addSection(document, "TERCERA: JORNADA Y REMUNERACIÓN", sectionFont);

            addParagraph(
                    document,
                    "EL DOCENTE prestará sus servicios en una jornada de "
                            + contract.weeklyHours() + " horas semanales, percibiendo como "
                            + "contraprestación por sus servicios una remuneración de S/ "
                            + contract.salary() + ", la cual será abonada de acuerdo con las "
                            + "condiciones y periodicidad establecidas por la institución.",
                    normalFont
            );

            addSection(document, "CUARTA: OBLIGACIONES DEL DOCENTE", sectionFont);

            addParagraph(
                    document,
                    "EL DOCENTE se obliga a: (i) cumplir con las actividades académicas "
                            + "asignadas; (ii) respetar el horario establecido por la institución; "
                            + "(iii) participar activamente en las actividades institucionales que "
                            + "se le convoque; y (iv) cumplir las normas, reglamentos y políticas "
                            + "internas del colegio.",
                    normalFont
            );

            addSection(document, "QUINTA: TERMINACIÓN", sectionFont);

            addParagraph(
                    document,
                    "El presente contrato podrá concluir por vencimiento del plazo "
                            + "establecido en la cláusula segunda, por mutuo acuerdo entre las "
                            + "partes, o por las demás causales previstas en la normativa "
                            + "laboral vigente aplicable.",
                    normalFont
            );

            addSection(document, "SEXTA: CONFORMIDAD", sectionFont);

            addParagraph(
                    document,
                    "Las partes declaran haber leído y comprendido el contenido íntegro "
                            + "del presente contrato y manifiestan su plena conformidad con "
                            + "todos sus términos y condiciones, suscribiéndolo en señal de "
                            + "aceptación.",
                    normalFont
            );

            document.add(new Paragraph(" "));

            Paragraph date = new Paragraph(
                    "Lima, " + formatDate(LocalDate.now()),
                    normalFont
            );

            date.setAlignment(Element.ALIGN_RIGHT);

            document.add(date);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            addGoldRule(document);

            document.add(new Paragraph(" "));

            PdfPTable signatures = new PdfPTable(2);
            signatures.setWidthPercentage(100);
            signatures.setWidths(new float[]{50, 50});

            PdfPCell schoolSignature = createSignatureCell(
                    "REPRESENTANTE DE LA INSTITUCIÓN",
                    "Nombre: __________________________",
                    "DNI: ______________________________",
                    normalFont,
                    boldFont
            );

            PdfPCell teacherSignature = createSignatureCell(
                    "EL DOCENTE",
                    "Nombre: " + getFullName(contract),
                    "DNI: " + contract.dni(),
                    normalFont,
                    boldFont
            );

            signatures.addCell(schoolSignature);
            signatures.addCell(teacherSignature);

            document.add(signatures);

            document.close();

            return outputStream.toByteArray();

        } catch (Exception e) {

            log.error("Error al generar contrato PDF para contractId={}", contract.id(), e);
            throw new BadRequestException("Error al generar contrato PDF");
        }
    }

    private void addSection(Document document, String title, Font font) throws DocumentException {

        Paragraph paragraph = new Paragraph(title, font);

        paragraph.setSpacingBefore(12);
        paragraph.setSpacingAfter(6);

        document.add(paragraph);
    }

    private void addGoldRule(Document document) throws DocumentException {

        LineSeparator line = new LineSeparator(1.2f, 100f, COLOR_DORADO, Element.ALIGN_CENTER, -2);

        Paragraph rule = new Paragraph();
        rule.add(new Chunk(line));
        rule.setSpacingAfter(10);

        document.add(rule);
    }

    private void addParagraph(Document document, String text, Font font) throws DocumentException {

        Paragraph paragraph = new Paragraph(text, font);

        paragraph.setAlignment(Element.ALIGN_JUSTIFIED);

        paragraph.setLeading(14);
        paragraph.setSpacingAfter(6);

        document.add(paragraph);
    }

    private void addSummaryTable(Document document, TeacherContractReportResponse contract)
            throws DocumentException {

        document.add(new Paragraph(" "));

        Font labelFont = new Font(Font.HELVETICA, 9, Font.BOLD, BLANCO);
        Font valueFont = new Font(Font.HELVETICA, 9, Font.NORMAL, GRIS_OSCURO);

        PdfPTable summary = createStyledTable(new float[]{35, 65});

        addRow(summary, "DOCENTE", getFullName(contract), labelFont, valueFont, false);
        addRow(summary, "DNI", safe(contract.dni(), "-"), labelFont, valueFont, true);
        addRow(summary, "ESPECIALIDAD", safe(contract.specialty(), "-"), labelFont, valueFont, false);
        addRow(summary, "MODALIDAD", safe(contract.contractType(), "-"), labelFont, valueFont, true);
        addRow(
                summary,
                "VIGENCIA",
                formatDate(contract.startDate()) + "   al   " + formatDate(contract.endDate()),
                labelFont, valueFont, false
        );
        addRow(summary, "JORNADA SEMANAL", contract.weeklyHours() + " horas", labelFont, valueFont, true);
        addRow(summary, "REMUNERACIÓN", "S/ " + contract.salary(), labelFont, valueFont, false);

        document.add(summary);
    }

    private String getFullName(TeacherContractReportResponse contract) {

        return String.join(
                " ",
                contract.firstName() != null ? contract.firstName() : "",
                contract.middleName() != null ? contract.middleName() : "",
                contract.paternalLastName() != null ? contract.paternalLastName() : "",
                contract.maternalLastName() != null ? contract.maternalLastName() : ""
        ).trim().replaceAll("\\s+", " ");
    }

    private String safe(String value, String fallback) {
        return (value == null || value.isBlank()) ? fallback : value;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    private PdfPTable createStyledTable(float[] widths) {

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setWidths(widths);
        table.setSpacingAfter(10);

        return table;
    }

    private void addRow(
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

    private PdfPCell createSignatureCell(
            String title,
            String name,
            String dni,
            Font normalFont,
            Font boldFont
    ) {

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

    private Paragraph spaced(String text, Font font) {
        Paragraph p = new Paragraph(text, font);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(3);
        return p;
    }

    private String formatDate(LocalDate date) {

        if (date == null) {
            return "-";
        }

        return date.format(DATE_FORMATTER);
    }

    private TeacherContract findById(Long contractId) {

        return teacherContractRepositoryPort.findById(contractId)
                .orElseThrow(() ->
                        new NotFoundException("No se encontró el contrato con id")
                );
    }

    private class HeaderFooterWatermarkEvent extends PdfPageEventHelper {

        private final CompanyResponse company;

        private final Image logo;

        HeaderFooterWatermarkEvent(CompanyResponse company) {
            this.company = company;
            this.logo = loadLogo(company);
        }

        private Image loadLogo(CompanyResponse company) {

            String logoUrl = company != null
                    ? company.logoUrl()
                    : null;

            if (logoUrl == null || logoUrl.isBlank()) {
                return null;
            }

            String url = logoUrl.startsWith("http")
                    ? logoUrl
                    : "http://localhost:8090/colegio/api/v1" + logoUrl;

            try {

                Image img = Image.getInstance(
                        URI.create(url).toURL().openStream().readAllBytes()
                );

                img.scaleToFit(70, BAND_HEIGHT - 30);

                return img;

            } catch (Exception e) {

                return null;
            }
        }
        @Override
        public void onEndPage(PdfWriter writer, Document document) {

            PdfContentByte canvas = writer.getDirectContent();

            drawHeaderBand(canvas);
            drawFooterBand(canvas, writer.getPageNumber());
            drawWatermark(canvas);
        }

        private void drawHeaderBand(PdfContentByte canvas) {

            canvas.saveState();

            // Franja superior
            canvas.setColorFill(COLOR_PRINCIPAL);
            canvas.rectangle(0, PAGE_HEIGHT - BAND_HEIGHT, PAGE_WIDTH, BAND_HEIGHT);
            canvas.fill();

            // Línea dorada bajo la franja
            canvas.setColorFill(COLOR_DORADO);
            canvas.rectangle(0, PAGE_HEIGHT - BAND_HEIGHT - 3, PAGE_WIDTH, 3);
            canvas.fill();

            canvas.restoreState();

            float textStartX = 50;

            if (logo != null) {

                float logoY = PAGE_HEIGHT - BAND_HEIGHT + (BAND_HEIGHT - logo.getScaledHeight()) / 2;
                logo.setAbsolutePosition(50, logoY);

                try {
                    canvas.addImage(logo);
                    textStartX = 50 + logo.getScaledWidth() + 15;
                } catch (Exception e) {
                    log.error("[Contrato PDF] Error al dibujar el logo en el header: {}", e.toString());
                }
            }

            String companyName = safe(company != null ? company.name() : null, "COLEGIO");
            String businessName = company != null ? company.businessName() : null;

            Font nameFont = new Font(Font.HELVETICA, 16, Font.BOLD, BLANCO);
            Font subtitleFont = new Font(Font.HELVETICA, 9, Font.NORMAL, COLOR_DORADO_SUAVE);

            ColumnText.showTextAligned(
                    canvas,
                    Element.ALIGN_LEFT,
                    new Phrase(companyName.toUpperCase(), nameFont),
                    textStartX,
                    PAGE_HEIGHT - BAND_HEIGHT / 2 + 6,
                    0
            );

            String subtitleLine = businessName != null && !businessName.isBlank()
                    ? businessName
                    : SUBTITULO_EMPRESA;

            ColumnText.showTextAligned(
                    canvas,
                    Element.ALIGN_LEFT,
                    new Phrase(subtitleLine, subtitleFont),
                    textStartX,
                    PAGE_HEIGHT - BAND_HEIGHT / 2 - 10,
                    0
            );
        }

        private void drawFooterBand(PdfContentByte canvas, int pageNumber) {

            canvas.saveState();

            canvas.setColorFill(COLOR_BORDE);
            canvas.rectangle(0, FOOTER_HEIGHT - 20, PAGE_WIDTH, 1);
            canvas.fill();

            canvas.restoreState();

            Font footerFont = new Font(Font.HELVETICA, 8, Font.NORMAL, COLOR_TEXTO_SECUNDARIO);

            StringBuilder footerText = new StringBuilder();

            if (company != null) {
                if (company.address() != null && !company.address().isBlank()) {
                    footerText.append(company.address());
                }
                if (company.phone() != null && !company.phone().isBlank()) {
                    if (!footerText.isEmpty()) footerText.append("  |  ");
                    footerText.append("Tel: ").append(company.phone());
                }
                if (company.website() != null && !company.website().isBlank()) {
                    if (!footerText.isEmpty()) footerText.append("  |  ");
                    footerText.append(company.website());
                }
            }

            ColumnText.showTextAligned(
                    canvas,
                    Element.ALIGN_LEFT,
                    new Phrase(footerText.toString(), footerFont),
                    50,
                    FOOTER_HEIGHT - 32,
                    0
            );

            ColumnText.showTextAligned(
                    canvas,
                    Element.ALIGN_RIGHT,
                    new Phrase("Página " + pageNumber, footerFont),
                    PAGE_WIDTH - 50,
                    FOOTER_HEIGHT - 32,
                    0
            );
        }

        private void drawWatermark(PdfContentByte canvas) {

            String watermarkText = safe(company != null ? company.name() : null, "COLEGIO").toUpperCase();

            PdfGState gState = new PdfGState();
            gState.setFillOpacity(0.06f);

            canvas.saveState();
            canvas.setGState(gState);
            canvas.setColorFill(COLOR_PRINCIPAL);

            Font watermarkFont = new Font(Font.HELVETICA, 60, Font.BOLD, COLOR_PRINCIPAL);

            ColumnText.showTextAligned(
                    canvas,
                    Element.ALIGN_CENTER,
                    new Phrase(watermarkText, watermarkFont),
                    PAGE_WIDTH / 2,
                    PAGE_HEIGHT / 2,
                    45
            );

            canvas.restoreState();
        }
    }
}