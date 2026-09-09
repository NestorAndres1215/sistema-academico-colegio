package com.colegio.backend.shared.report.pdf;

import com.colegio.backend.modules.companies.application.dto.CompanyResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import lombok.RequiredArgsConstructor;

import java.net.URI;

import static com.colegio.backend.shared.report.pdf.PdfStyle.*;
import static com.colegio.backend.shared.report.pdf.PdfStyle.BLANCO;
import static com.colegio.backend.shared.report.pdf.PdfStyle.COLOR_BORDE;
import static com.colegio.backend.shared.report.pdf.PdfStyle.COLOR_DORADO;
import static com.colegio.backend.shared.report.pdf.PdfStyle.COLOR_DORADO_SUAVE;
import static com.colegio.backend.shared.report.pdf.PdfStyle.COLOR_PRINCIPAL;
import static com.colegio.backend.shared.report.pdf.PdfStyle.COLOR_TEXTO_SECUNDARIO;
import static com.colegio.backend.shared.report.pdf.PdfStyle.FOOTER_HEIGHT;
import static com.colegio.backend.shared.report.pdf.PdfStyle.PAGE_HEIGHT;
import static com.colegio.backend.shared.report.pdf.PdfStyle.PAGE_WIDTH;
import static com.colegio.backend.shared.report.pdf.PdfStyle.SUBTITULO_EMPRESA;
import static com.colegio.backend.shared.utils.StringUtils.safe;


public class HeaderFooterWatermarkEvent extends PdfPageEventHelper {

    private final CompanyResponse company;

    private final Image logo;

    // Inicializa los datos de la empresa y carga su logotipo.
    public HeaderFooterWatermarkEvent(CompanyResponse company) {
        this.company = company;
        this.logo = loadLogo(company);
    }

    // Carga el logotipo de la empresa desde la URL configurada.
    private Image loadLogo(CompanyResponse company) {

        if (company == null || company.logoUrl() == null) {
            return null;
        }

        String logoUrl = company.logoUrl();

        String url = logoUrl.startsWith("http")
                ? logoUrl
                : "http://localhost:8090/colegio/api/v1" + logoUrl;

        try {

            Image img = Image.getInstance(URI.create(url).toURL().openStream().readAllBytes());

            img.scaleToFit(70, BAND_HEIGHT - 30);

            return img;

        } catch (Exception e) {

            return null;
        }
    }

    // Se ejecuta al finalizar cada página del documento PDF.
    @Override
    public void onEndPage(PdfWriter writer, Document document) {

        PdfContentByte canvas = writer.getDirectContent();

        drawHeaderBand(canvas);
        drawFooterBand(canvas, writer.getPageNumber());
        drawWatermark(canvas);
    }

    // Dibuja la cabecera institucional en la parte superior de la página.
    private void drawHeaderBand(PdfContentByte canvas) {

        canvas.saveState();

        canvas.setColorFill(COLOR_PRINCIPAL);
        canvas.rectangle(0, PAGE_HEIGHT - BAND_HEIGHT, PAGE_WIDTH, BAND_HEIGHT);
        canvas.fill();

        canvas.setColorFill(COLOR_DORADO);
        canvas.rectangle(0, PAGE_HEIGHT - BAND_HEIGHT - 3, PAGE_WIDTH, 3);
        canvas.fill();

        canvas.restoreState();

        float textStartX = 50;

        if (logo != null) {

            float logoY = PAGE_HEIGHT - BAND_HEIGHT + (BAND_HEIGHT - logo.getScaledHeight()) / 2;
            logo.setAbsolutePosition(50, logoY);
            canvas.addImage(logo);
            textStartX = 50 + logo.getScaledWidth() + 15;

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

    // Dibuja el pie de página con los datos de la institución y el número de página.
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


    // Dibuja el logotipo institucional como marca de agua en el centro de la página.
    private void drawWatermark(PdfContentByte canvas) {

        if (logo == null) {
            return;
        }

        try {
            Image watermark = Image.getInstance(logo);

            watermark.scaleToFit(400, 350);

            float x = (PAGE_WIDTH - watermark.getScaledWidth()) / 2;
            float y = (PAGE_HEIGHT - watermark.getScaledHeight()) / 2;

            PdfGState gState = new PdfGState();
            gState.setFillOpacity(0.06f);

            canvas.saveState();
            canvas.setGState(gState);

            watermark.setAbsolutePosition(x, y);
            canvas.addImage(watermark);

            canvas.restoreState();

        } catch (Exception e) {
           throw  new RuntimeException(e);
        }
    }

}
