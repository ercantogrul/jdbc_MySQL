package jdbc.Mentor.project;

public class Lesson {
    //lesson_id
    //lesson_name
    private String lesson_name;
    private int lesson_id;

    //constructor
    public Lesson(String lesson_name, int lesson_id) {
        this.lesson_name = lesson_name;
        this.lesson_id = lesson_id;
    }

    //getter-setter
    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    //toString method
    @Override
    public String toString() {
        return "Lesson{" + "lesson_name=" + lesson_name + ", lesson_id=" + lesson_id + '}';

    }
}