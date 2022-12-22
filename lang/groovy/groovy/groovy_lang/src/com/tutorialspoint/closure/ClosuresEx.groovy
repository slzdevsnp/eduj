package com.tutorialspoint.closure

def clos1 = {println "hello world"}

clos1.call()

def closwPar =  { x -> println "hello ${x}"}

closwPar.call("Civilizations")

//closures can refer to variables in the same scope
def str1 = "Hello"
def clos2 = {x -> println "${str1} ${x}"}
clos2.call("Friend")

str1 = "Welcome"
clos2.call("Friend")

//using closures as method params

def Display(cl, String msg){
    cl.call(msg)
}

Display({x -> println "Hello ${x}"}, "Virginia")

//using closures with collections
def lst = [11,12,13,14]
lst.each {println it}

lst.each{ if(it %2 == 0) println "paired el: ${it}"}


//with dicts

def mp = ["TopicName" : "Maps", "TopicDescription" : "Methods in Maps"]
mp.each {println "${it.key} maps to: ${it.value}"}
