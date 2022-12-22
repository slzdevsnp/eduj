package packt.j9fprog.genericMethods.model;

public class Grade {
    private final int score;
    private final String gradeString;

    public Grade(int score, String gradeString) {
        this.score = score;
        this.gradeString = gradeString;
    }

    public int getScore() {
        return score;
    }

    public String getGradeString() {
        return gradeString;
    }

    @Override
    public String toString(){
        return ("Grade: score:"+this.score + " with grade:"+this.gradeString);
    }
}
