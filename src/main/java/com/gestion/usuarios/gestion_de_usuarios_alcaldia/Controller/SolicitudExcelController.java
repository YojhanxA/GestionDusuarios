package com.gestion.usuarios.gestion_de_usuarios_alcaldia.Controller;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gestion.usuarios.gestion_de_usuarios_alcaldia.entities.Solicitud;
import com.gestion.usuarios.gestion_de_usuarios_alcaldia.repository.SolicitudRepository;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudExcelController {
    @Autowired
    private SolicitudRepository solicitudRepository;

    @PostMapping("/upload-excel")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            // Leer cabecera
            Row header = sheet.getRow(0);
            Map<Integer, String> columnas = new HashMap<>();
            for (Cell cell : header) {
                String nombreColumna = normalizar(cell.getStringCellValue());
                columnas.put(cell.getColumnIndex(), nombreColumna);
            }

            // Leer filas
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row fila = sheet.getRow(i);
                if (fila == null)
                    continue;

                Solicitud solicitud = new Solicitud();
                for (Cell cell : fila) {
                    String key = columnas.get(cell.getColumnIndex());
                    String valor = obtenerValorCelda(cell);

                    switch (key) {
                        case "nombre" -> solicitud.setNombre(valor);
                        case "apellido" -> solicitud.setApellido(valor);
                        case "cedula" -> solicitud.setCedula(valor);
                        case "correo" -> solicitud.setCorreo(valor);
                        case "secretaria" -> solicitud.setSecretaria(valor);
                        case "subsecretaria" -> solicitud.setSubsecretaria(valor);
                        case "vinculado" ->
                            solicitud.setVinculado(valor.equalsIgnoreCase("sí") || valor.equalsIgnoreCase("true"));
                        case "fecha_inicio_contrato" -> {
                            if (!valor.isBlank())
                                solicitud.setFechaInicioContrato(LocalDate.parse(valor));
                        }
                        case "fecha_fin_contrato" -> {
                            if (!valor.isBlank())
                                solicitud.setFechaFinContrato(LocalDate.parse(valor));
                        }
                    }
                }
                solicitud.setCuentaCreada(false); // por defecto
                solicitudRepository.save(solicitud);
            }

            workbook.close();
            return ResponseEntity.ok("Archivo procesado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al procesar el archivo Excel.");
        }
    }

    private String normalizar(String nombreColumna) {
        return nombreColumna.trim().toLowerCase().replace(" ", "_")
                .replace("nombres", "nombre")
                .replace("apellidos", "apellido")
                .replace("identificación", "cedula")
                .replace("correo_electronico", "correo");
    }

    private String obtenerValorCelda(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getLocalDateTimeCellValue().toLocalDate().toString()
                    : String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }
}
