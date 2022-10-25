package com.test.agent.controller;


import com.test.agent.entity.Agent;
import com.test.agent.repository.AgentRepository;
import com.test.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/api/v1")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentRepository agentRepository;

    @GetMapping("/agents")
    public ResponseEntity<List<Agent>> getAllAgents(){
        return new ResponseEntity<>(agentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/agent")
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent){
        return new ResponseEntity<>(agentService.save(agent), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAgent(@PathVariable(value = "id") Long id){
        agentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{name}")
    Agent replaceAgent(@RequestBody Agent newAgent, @PathVariable String name) {

        return agentRepository.findAgentByName(name)
                .map(agent -> {
                    agent.setOs(newAgent.getOs());
                    agent.setDateAdd(newAgent.getDateAdd());
                    agent.setIp(newAgent.getIp());
                    agent.setName(newAgent.getName());
                    agent.setLastKeepAlive(newAgent.getLastKeepAlive());
                    agent.setStatus(newAgent.getStatus());
                    agent.setVersion(newAgent.getVersion());
                    return agentRepository.save(agent);
                })
                .orElseGet(() -> {
                    newAgent.setName(name);
                    return agentRepository.save(newAgent);
                });
    }
}
