package com.tutorialspoint.oo

class Student{
    private int StudentID;
    private String StudentName;

     void setStudentID(int pID) {
        StudentID = pID;
    }

    void setStudentName(String pName) {
        StudentName = pName;
    }

    int getStudentID() {
        return this.StudentID;
    }

    String getStudentName() {
        return this.StudentName;
    }
}

Student st = new Student()
st.setStudentID(1)
st.setStudentName('Joe')

println "student id: ${st.getStudentID()} name: ${st.getStudentName()}"
