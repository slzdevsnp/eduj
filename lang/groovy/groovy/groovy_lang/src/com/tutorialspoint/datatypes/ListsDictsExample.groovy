package com.tutorialspoint.datatypes

def l= ['Angular', 'Groovy', 'Java']

println  "el 0: ${l[0]}"

l.add('Pyhton')
println "after element added: ${l}"
println "indexed element: ${l.get(1)}"

def d = ['item1' : 'lists', 'item2' : 'maps' ]

println "${d.get('item1')}"
println "dictionary values: ${d.values()}"