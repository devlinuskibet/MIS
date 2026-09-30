package com.example.church_management_system.controller.sermon;

import com.example.church_management_system.dto.sermon.SermonDto;
import com.example.church_management_system.service.sermon.SermonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/sermons")
public class SermonController {

    private final SermonService sermonService;

    @Autowired
    public SermonController(SermonService sermonService) {
        this.sermonService = sermonService;
    }

    @PostMapping
    public ResponseEntity<SermonDto> createSermon(@RequestBody SermonDto sermonDto) {
        SermonDto created = sermonService.createSermon(sermonDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SermonDto> updateSermon(@PathVariable Long id, @RequestBody SermonDto sermonDto) {
        SermonDto updated = sermonService.updateSermon(id, sermonDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSermon(@PathVariable Long id) {
        sermonService.deleteSermon(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SermonDto>> getAllSermons() {
        List<SermonDto> sermons = sermonService.findAllSermons();
        return ResponseEntity.ok(sermons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SermonDto> getSermonById(@PathVariable Long id) {
        SermonDto sermon = sermonService.getSermonById(id);
        return ResponseEntity.ok(sermon);
    }
}
