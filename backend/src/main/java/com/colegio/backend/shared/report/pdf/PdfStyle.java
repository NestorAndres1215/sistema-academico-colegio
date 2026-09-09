package com.colegio.backend.shared.report.pdf;

import com.lowagie.text.PageSize;

import java.awt.*;

public final class PdfStyle {

    private PdfStyle() {
    }

    public static final String SUBTITULO_EMPRESA = "Sistema de Gestión Educativa";

    // ---- Paleta de colores institucional ----
    public static final Color COLOR_PRINCIPAL = new Color(16, 38, 74);
    public static final Color COLOR_PRINCIPAL_OSCURO = new Color(10, 26, 53);
    public static final Color COLOR_DORADO = new Color(196, 160, 75);
    public static final Color COLOR_DORADO_SUAVE = new Color(224, 199, 138);
    public static final Color COLOR_TEXTO_SECUNDARIO = new Color(115, 126, 148);
    public static final Color COLOR_FILA_PAR = new Color(244, 246, 250);
    public static final Color COLOR_BORDE = new Color(224, 229, 238);
    public static final Color COLOR_BORDE_TABLA = new Color(206, 214, 228);
    public static final Color BLANCO = Color.WHITE;
    public static final Color GRIS_OSCURO = new Color(38, 42, 54);

    public static final float BAND_HEIGHT = 100f;
    public static final float FOOTER_HEIGHT = 50f;
    public static final float PAGE_WIDTH = PageSize.A4.getWidth();
    public static final float PAGE_HEIGHT = PageSize.A4.getHeight();
}