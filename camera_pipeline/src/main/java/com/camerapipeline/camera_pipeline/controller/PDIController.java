package com.camerapipeline.camera_pipeline.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.camerapipeline.camera_pipeline.dto.PdiDTO;
import com.camerapipeline.camera_pipeline.mapper.pdi.PdiMapper;
import com.camerapipeline.camera_pipeline.services.pdi.PDIService;

@RestController
@RequestMapping("/pdi")
public class PDIController {
    @Autowired
    PDIService pdiService;
    @Autowired
    PdiMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<PdiDTO>> getAll() {
        List<PdiDTO> list = mapper.toDTOList(
            pdiService.getPDIList()
        );
        return new ResponseEntity<List<PdiDTO>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PdiDTO> getPDI(@PathVariable("id") Integer id) {
        PdiDTO dto = mapper.toDTO(
            pdiService.getPDI(id)
        );
        return new ResponseEntity<PdiDTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<PdiDTO> addPDI(@Valid @RequestBody PdiDTO dto) {
        PdiDTO groupDTO = mapper.toDTO(
            pdiService.savePDI(
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<PdiDTO>(groupDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PdiDTO> updatePDI(@PathVariable("id") Integer id, @Valid @RequestBody PdiDTO dto) {
        PdiDTO groupDTO = mapper.toDTO(
            pdiService.updatePDI(
                id,
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<PdiDTO>(
            groupDTO,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePDI(@PathVariable("id") Integer id) {
        pdiService.deletePDI(id);
        return ResponseEntity.noContent().build();
    }
}
