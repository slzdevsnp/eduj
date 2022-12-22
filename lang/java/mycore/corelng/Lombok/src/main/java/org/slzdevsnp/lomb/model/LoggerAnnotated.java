package org.slzdevsnp.lomb.model;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class LoggerAnnotated {

    public void say(){
        log.info("saying..");
    }
    public void cry(){
        log.debug("crying..");
    }
}
