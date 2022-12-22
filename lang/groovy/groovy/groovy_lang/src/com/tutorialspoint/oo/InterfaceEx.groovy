package com.tutorialspoint.oo


interface Marks{
    void DisplayMarks()
}

class Pupil implements Marks {

    int StudentId
    int Marks1

    void DisplayMarks(){
        println Marks1
    }
}


def p = new Pupil()
p.StudentId = 1
p.Marks1 = 5
p.DisplayMarks()
