import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentRecord {
    private SimpleStringProperty studentID;
    private SimpleFloatProperty midterm;
    private SimpleFloatProperty assignments;
    private SimpleFloatProperty finalExam;
    private SimpleFloatProperty finalMark;
    private SimpleStringProperty letterGrade;

    public StudentRecord(String ID, float assignments, float midterm, float finalExam) {
        this.studentID = new SimpleStringProperty(ID);
        this.assignments = new SimpleFloatProperty(assignments);
        this.midterm = new SimpleFloatProperty(midterm);
        this.finalExam = new SimpleFloatProperty(finalExam);
        this.finalMark = new SimpleFloatProperty(assignments * 0.2f + midterm * 0.3f + finalExam * 0.5f);
        this.letterGrade = new SimpleStringProperty(determineGrade(finalMark.get()));
    }

    private String determineGrade(float finalMark){
        if (finalMark >= 80 && finalMark <= 100)
            return "A";
        else if (finalMark >= 70 && finalMark < 80)
            return "B";
        else if (finalMark >= 60 && finalMark < 70)
            return "C";
        else if (finalMark >= 50 && finalMark < 60)
            return "D";
        else
            return "F";
    }

    public String getStudentID(){
        return studentID.get();
    }

    public float getMidterm(){
        return midterm.get();
    }

    public float getAssignments(){
        return assignments.get();
    }

    public float getFinalExam(){
        return finalExam.get();
    }

    public float getFinalMark(){
        return finalMark.get();
    }

    public String getLetterGrade(){
        return letterGrade.get();
    }
}