package com.test.agent.repository;

import com.test.agent.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    void deleteAgentById(Long id);
    Optional<Agent> findAgentById(Long id);
    Optional<Agent> findAgentByName(String name);
}
