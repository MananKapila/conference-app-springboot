package com.tgt.demoproj.controllers;

import com.tgt.demoproj.models.Session;
import com.tgt.demoproj.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> sessionList(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session getSession(@PathVariable Long id){
        return sessionRepository.getReferenceById(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        //Also need to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public Session update(@PathVariable Long id, @RequestBody Session session){
        //because this is PUT, we expect all attributed to be passed in.
        // A patch would only need what needs to be updated
        //TODO: Add validation that all attributes are passed in, else return 400 bad payload
        Session existingSession = sessionRepository.getReferenceById(id);
        BeanUtils.copyProperties(session,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
