package com.tutorialspoint.oo

import javax.swing.plaf.BorderUIResource

class Person {
    public String name;
    public Person() {}
}

class Etudiant extends Person {
    int StudentID
    int Marks1;

    public Etudiant() {
        super();
    }
}


Etudiant st = new Etudiant()

st.name = 'John Weak'
st.StudentID = 1
st.Marks1 = 10.0
