package org.github.slzdevsnp.conferencedemo.repositories;

import org.github.slzdevsnp.conferencedemo.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
