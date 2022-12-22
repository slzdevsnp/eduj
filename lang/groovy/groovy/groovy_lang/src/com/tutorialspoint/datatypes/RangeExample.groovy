package com.tutorialspoint.datatypes



def range = 0..5

println(range)
println(range.get(2))

def exclusveRange = 0..<5
println exclusveRange

def chr = 'a'..'x'
println "range as a seq of chars ${chr}"

println "chRange  lower value: ${chr.getFrom()} upper ${chr.getTo()}, at position 2: ${chr.get(2)}, contains w? ${chr.contains('w')}"