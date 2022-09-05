package com.tgt.demoproj.controllers;

import com.tgt.demoproj.models.Session;
import com.tgt.demoproj.models.Speaker;
import com.tgt.demoproj.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    public List<Speaker> speakerList(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Speaker getSpeaker(@PathVariable Long id){
        return speakerRepository.getReferenceById(id);
    }

    @PostMapping
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Also need to check for children records before deleting
        speakerRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public Speaker update(@PathVariable Long id, @RequestBody Speaker speaker){
        //because this is PUT, we expect all attributed to be passed in.
        // A patch would only need what needs to be updated
        //TODO: Add validation that all attributes are passed in, else return 400 bad payload
        Speaker existingSpeaker = speakerRepository.getReferenceById(id);
        BeanUtils.copyProperties(speaker,existingSpeaker,"speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
