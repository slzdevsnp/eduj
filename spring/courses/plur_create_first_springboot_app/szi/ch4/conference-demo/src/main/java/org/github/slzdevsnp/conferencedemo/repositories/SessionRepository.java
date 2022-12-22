package org.github.slzdevsnp.conferencedemo.repositories;


import org.github.slzdevsnp.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}