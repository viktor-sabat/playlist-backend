package ucll.backend_basics.eindopdrachtviktorsabat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ucll.backend_basics.eindopdrachtviktorsabat.model.Song;
import ucll.backend_basics.eindopdrachtviktorsabat.service.SongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/song")
public class SongController {
    private SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.createSong(song);
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        return songService.updateSong(id, updatedSong);
    }

    @GetMapping("/{album}")
    public List<Song> getSongsByAlbum(@PathVariable String album) {
        return songService.getSongsByAlbum(album);
    }

    @GetMapping("/{minLength}/{maxLength}")
    public List<Song> getSongsByLengthRange(@PathVariable Integer minLength, @PathVariable Integer maxLength) {
        return songService.getSongsByLengthRange(minLength, maxLength);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getFieldErrors()) {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }
        return errors;
    }
}



