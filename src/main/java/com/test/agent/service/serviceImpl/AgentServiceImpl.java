package com.test.agent.service.serviceImpl;

import com.test.agent.entity.Agent;
import com.test.agent.exception.AgentNotFoundException;
import com.test.agent.repository.AgentRepository;
import com.test.agent.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Agent save(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent findAgentById(Long id) {
        return agentRepository.findAgentById(id).orElseThrow(() -> new AgentNotFoundException("Agent by id " + id + " was not found"));
    }

    @Override
    public Agent findAgentByName(String name) {
        return agentRepository.findAgentByName(name).orElseThrow(() -> new AgentNotFoundException("Agent by name " + name + " was not found"));
    }

    @Override
    public List<Agent> findAll() {
        return agentRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        agentRepository.deleteAgentById(id);
    }

    @Override
    @Transactional
    public long getCountOfAgents() {
        long count = agentRepository.count();
        return count;
    }

    @Override
    public List<Agent> findAgentByStatus(String status) {
            return agentRepository.findAgentByStatus(status).orElse(null);
    }

    /*
    @Override
    public int countAgentByStatus(String status) {
        switch (status){
            case "active":
        }

        return 0;

    } **/


}
