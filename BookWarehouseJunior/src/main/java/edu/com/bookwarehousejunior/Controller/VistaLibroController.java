package edu.com.bookwarehousejunior.Controller;

import edu.com.bookwarehousejunior.Model.Dto.LibroGlobalDto;
import edu.com.bookwarehousejunior.Model.Dto.VistaLibroDTO;
import edu.com.bookwarehousejunior.Model.Dto.VistaLibroDTO2;
import edu.com.bookwarehousejunior.Service.VistaLibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/vista-libros")
public class VistaLibroController {

    @Autowired
    private VistaLibroService vistaLibroService;

    @GetMapping
    public List<VistaLibroDTO> obtenerVistaLibros() {
        return vistaLibroService.obtenerLibros();
    }

    //
    @GetMapping("/2")
    public ResponseEntity<List<VistaLibroDTO2>> obtenerLibros2() {
        List<VistaLibroDTO2> libros = vistaLibroService.obtenerLibros2();
        return ResponseEntity.ok(libros);
    }
}