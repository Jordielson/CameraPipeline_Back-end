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

import com.camerapipeline.camera_pipeline.dto.GroupPipelineDTO;
import com.camerapipeline.camera_pipeline.mapper.pipeline.GroupPipelineMapper;
import com.camerapipeline.camera_pipeline.services.pipeline.GroupPipelineService;

@RestController
@RequestMapping("/group-pipeline")
public class GroupPipelineController {
    @Autowired
    GroupPipelineService groupService;
    @Autowired
    GroupPipelineMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<GroupPipelineDTO>> getAll() {
        List<GroupPipelineDTO> list = mapper.toDTOList(
            groupService.getGroupPipelineList()
        );
        return new ResponseEntity<List<GroupPipelineDTO>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<GroupPipelineDTO> getGroup(@PathVariable("id") Integer id) {
        GroupPipelineDTO dto = mapper.toDTO(
            groupService.getGroupPipeline(id)
        );
        return new ResponseEntity<GroupPipelineDTO>(dto, HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<GroupPipelineDTO> addGroup(@Valid @RequestBody GroupPipelineDTO dto) {
        GroupPipelineDTO groupDTO = mapper.toDTO(
            groupService.saveGroupPipeline(
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<GroupPipelineDTO>(groupDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupPipelineDTO> updateGroup(@PathVariable("id") Integer id, @Valid @RequestBody GroupPipelineDTO dto) {
        GroupPipelineDTO groupDTO = mapper.toDTO(
            groupService.updateGroupPipeline(
                id,
                mapper.fromDTO(dto)
            )
        );
        return new ResponseEntity<GroupPipelineDTO>(
            groupDTO,
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable("id") Integer id) {
        groupService.deleteGroupPipeline(id);
        return ResponseEntity.noContent().build();
    }
}
