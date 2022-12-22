package com.tutorialspoint.dsl

class EmailDsl {
    String toText
    String fromText
    String body

    /**
     * This method accepts a closure which is essentially the DSL. Delegate the
     * closure methods to
     * the DSL class so the calls can be processed
     */

    def static make(closure) {
        EmailDsl emailDsl = new EmailDsl()
        // any method called in closure will be delegated to the EmailDsl class
        closure.delegate = emailDsl
        closure()
        emailDsl.printMsg()
    }

    /**
     * Store the parameter as a variable and use it later to output a memo
     */

    def to(String toText) {
        this.toText = toText
    }

    def from(String fromText) {
        this.fromText = fromText
    }

    def body(String bodyText) {
        this.body = bodyText
    }

    def printMsg(){
        println "from: ${this.fromText} to: ${this.toText} message: ${this.body}"
    }
}


EmailDsl.make {
    to "Nirav Assar"
    from "Barack Obama"
    body "How are things? We are doing well. Take care"
}
