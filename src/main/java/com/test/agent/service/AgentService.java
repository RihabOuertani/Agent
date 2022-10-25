package com.test.agent.service;

import com.test.agent.entity.Agent;

import java.util.List;


public interface AgentService {
    public Agent save(Agent agent);
    public Agent findAgentById(Long id);
    public Agent findAgentByName(String name);
    public List<Agent> findAll();
    public void delete(Long id);
    //public Agent update(Agent agent);

}
