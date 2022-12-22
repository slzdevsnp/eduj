package com.tutorialspoint.oo

abstract class AbstractPerson {
    public String name;
    public AbstractPerson() { }
    abstract void DisplayMarks();
}

class RealStudent extends AbstractPerson {
    int StudentID
    int Marks1;

    public RealStudent() {
        super();
    }

    //implementation
    void DisplayMarks() {
        println(Marks1);
    }
}

def st = new RealStudent()
st.StudentID = 1
st.name = "Joe"
st.Marks1 = 10
println st.name
println st.DisplayMarks()