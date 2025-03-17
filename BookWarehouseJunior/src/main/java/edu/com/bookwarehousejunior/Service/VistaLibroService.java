package edu.com.bookwarehousejunior.Service;

import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.com.bookwarehousejunior.Model.Dto.LibroGlobalDto;
import edu.com.bookwarehousejunior.Model.Dto.VistaLibroDTO;
import edu.com.bookwarehousejunior.Model.Dto.VistaLibroDTO2;
import edu.com.bookwarehousejunior.Repo.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VistaLibroService {

    @Autowired
    private LibroRepository vistaLibroRepository;

    public List<VistaLibroDTO> obtenerLibros() {
        List<Object[]> resultados = vistaLibroRepository.obtenerVistaLibros();

        return resultados.stream().map(obj -> new VistaLibroDTO(
                (String) obj[0], // libro
                (BigDecimal) obj[1], // peso
                (Integer) obj[2], // numeroPaginas
                convertirFecha(obj[3]), // fechaPublicacion
                (BigDecimal) obj[4], // precio
                (String) obj[5], // editorial
                (String) obj[6], // almacen
                (String) obj[7], // categoria
                (String) obj[8]  // autores
        )).collect(Collectors.toList());
    }

    private LocalDate convertirFecha(Object fecha) {
        if (fecha instanceof Date) {
            return ((Date) fecha).toLocalDate();
        }
        return null;
    }


    // vista 2

    @Autowired
    private ObjectMapper objectMapper; // Asegúrate de importar Jackson



    public List<VistaLibroDTO2> obtenerLibros2() {
        List<Object[]> resultados = vistaLibroRepository.obtenerLibrosDesdeVista();
        return resultados.stream().map(obj -> new
                VistaLibroDTO2(
                (Integer) obj[0],
                (String) obj[1],
                (BigDecimal) obj[2],
                (Integer) obj[3],
                ((Date) obj[4]).toLocalDate(), // Manejo de fecha
                (BigDecimal) obj[5],
                (String) obj[6],
                (String) obj[7],
                (String) obj[8],
                convertirJsonALista((String) obj[9]) // Convierte JSON a List<String>
        )).collect(Collectors.toList());
    }

    private List<String> convertirJsonALista(String json) {
        try {
            return Arrays.asList(objectMapper.readValue(json, String[].class));
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }



}
