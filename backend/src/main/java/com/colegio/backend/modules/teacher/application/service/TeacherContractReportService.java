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
import com.colegio.backend.shared.report.pdf.HeaderFooterWatermarkEvent;
import com.colegio.backend.shared.report.pdf.PdfElements;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import static com.colegio.backend.shared.report.pdf.PdfStyle.*;
import static com.colegio.backend.shared.utils.DateUtils.formatDate;
import static com.colegio.backend.shared.utils.StringUtils.*;

@RequiredArgsConstructor
@Service
public class TeacherContractReportService implements TeacherContractReportUseCase {

    private final TeacherContractRepositoryPort teacherContractRepositoryPort;
    private final TeacherContractReportMapper teacherContractReportMapper;
    private final CompanyUseCase companyUseCase;
    private final PdfElements pdfElements;


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

            document.add(pdfElements.createCenteredTitle("CONTRATO DE PRESTACIÓN DE SERVICIOS DOCENTES", titleFont));

            document.add(pdfElements.createCenteredReference("N.° de contrato: " + contract.id(), italicFont));

            pdfElements.addGoldRule(document);

            pdfElements.addParagraph(
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

            pdfElements.addSection(document, "PRIMERA: OBJETO DEL CONTRATO", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "El presente contrato tiene por objeto establecer los términos y "
                            + "condiciones bajo los cuales EL DOCENTE prestará servicios de "
                            + "enseñanza en " + safe(company.name(), "la institución educativa")
                            + ", desarrollando las actividades académicas correspondientes a "
                            + "su especialidad en " + safe(contract.specialty(), "la materia asignada") + ".",
                    normalFont
            );

            pdfElements.addSection(document, "SEGUNDA: PLAZO Y VIGENCIA", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "El presente contrato se celebra bajo la modalidad de "
                            + safe(contract.contractType(), "-").toLowerCase()
                            + " y tendrá vigencia desde el " + formatDate(contract.startDate())
                            + " hasta el " + formatDate(contract.endDate())
                            + ", fecha en la cual concluirá automáticamente sin necesidad de "
                            + "aviso previo, salvo que las partes acuerden su renovación.",
                    normalFont
            );

            pdfElements.addSection(document, "TERCERA: JORNADA Y REMUNERACIÓN", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "EL DOCENTE prestará sus servicios en una jornada de "
                            + contract.weeklyHours() + " horas semanales, percibiendo como "
                            + "contraprestación por sus servicios una remuneración de S/ "
                            + contract.salary() + ", la cual será abonada de acuerdo con las "
                            + "condiciones y periodicidad establecidas por la institución.",
                    normalFont
            );

            pdfElements.addSection(document, "CUARTA: OBLIGACIONES DEL DOCENTE", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "EL DOCENTE se obliga a: (i) cumplir con las actividades académicas "
                            + "asignadas; (ii) respetar el horario establecido por la institución; "
                            + "(iii) participar activamente en las actividades institucionales que "
                            + "se le convoque; y (iv) cumplir las normas, reglamentos y políticas "
                            + "internas del colegio.",
                    normalFont
            );

            pdfElements.addSection(document, "QUINTA: TERMINACIÓN", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "El presente contrato podrá concluir por vencimiento del plazo "
                            + "establecido en la cláusula segunda, por mutuo acuerdo entre las "
                            + "partes, o por las demás causales previstas en la normativa "
                            + "laboral vigente aplicable.",
                    normalFont
            );

            pdfElements.addSection(document, "SEXTA: CONFORMIDAD", sectionFont);

            pdfElements.addParagraph(
                    document,
                    "Las partes declaran haber leído y comprendido el contenido íntegro "
                            + "del presente contrato y manifiestan su plena conformidad con "
                            + "todos sus términos y condiciones, suscribiéndolo en señal de "
                            + "aceptación.",
                    normalFont
            );

            document.add(new Paragraph(" "));

            document.add(pdfElements.createRightAlignedParagraph("Lima, " + formatDate(LocalDate.now()), normalFont));

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            pdfElements.addGoldRule(document);

            document.add(new Paragraph(" "));

            PdfPTable signatures = new PdfPTable(2);
            signatures.setWidthPercentage(100);
            signatures.setWidths(new float[]{50, 50});

            PdfPCell schoolSignature =pdfElements.createSignatureCell(
                    "REPRESENTANTE DE LA INSTITUCIÓN",
                    "Nombre: __________________________",
                    "DNI: ______________________________",
                    normalFont,
                    boldFont
            );

            PdfPCell teacherSignature = pdfElements.createSignatureCell(
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

            throw new BadRequestException("Error al generar contrato PDF");
        }
    }

    // Crea y agrega la tabla con el resumen de información del contrato.
    private void addSummaryTable(Document document, TeacherContractReportResponse contract) throws DocumentException {

        document.add(new Paragraph(" "));

        Font labelFont = new Font(Font.HELVETICA, 9, Font.BOLD, BLANCO);
        Font valueFont = new Font(Font.HELVETICA, 9, Font.NORMAL, GRIS_OSCURO);

        PdfPTable summary =pdfElements.createStyledTable(new float[]{35, 65});

        pdfElements.addRow(summary, "DOCENTE", getFullName(contract), labelFont, valueFont, false);
        pdfElements.addRow(summary, "DNI", safe(contract.dni(), "-"), labelFont, valueFont, true);
        pdfElements.addRow(summary, "ESPECIALIDAD", safe(contract.specialty(), "-"), labelFont, valueFont, false);
        pdfElements.addRow(summary, "MODALIDAD", safe(contract.contractType(), "-"), labelFont, valueFont, true);
        pdfElements.addRow(summary, "VIGENCIA", formatContractValidity(contract), labelFont, valueFont, false);
        pdfElements.addRow(summary, "JORNADA SEMANAL", contract.weeklyHours() + " horas", labelFont, valueFont, true);
        pdfElements.addRow(summary, "REMUNERACIÓN", "S/ " + contract.salary(), labelFont, valueFont, false);

        document.add(summary);
    }

    // Construye el nombre completo del docente.
    private String getFullName(TeacherContractReportResponse contract) {

        return String.join(
                " ",
                contract.firstName() != null ? contract.firstName() : "",
                contract.middleName() != null ? contract.middleName() : "",
                contract.paternalLastName() != null ? contract.paternalLastName() : "",
                contract.maternalLastName() != null ? contract.maternalLastName() : ""
        ).trim().replaceAll("\\s+", " ");
    }

    private TeacherContract findById(Long contractId) {
        return teacherContractRepositoryPort.findById(contractId)
                .orElseThrow(() -> new NotFoundException("No se encontró el contrato con id"));
    }

    private String formatContractValidity(TeacherContractReportResponse contract) {

        return formatDate(contract.startDate()) + "   al   " + formatDate(contract.endDate());
    }

}