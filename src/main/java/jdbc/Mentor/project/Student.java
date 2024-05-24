package jdbc.Mentor.project;

public class Student {

    // id, name, lesson_id
    private int id;
    private String name;
    private int lesson_id;

    public Student(String name, int lesson_id) {
      //  this.id = id;
        this.name = name;
        this.lesson_id = lesson_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lesson_id=" + lesson_id +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

}
