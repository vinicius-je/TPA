package entities;

public class Student implements Comparable<Student> {
    private Integer id;
    private String name;
    private Double testScore;

    public Student(Integer id, String name, Double testScore){
        this.id = id;
        this.name = name;
        this.testScore = testScore;
    }

    public Student(Integer id){
        this.id = id;
    }

    public Student(String name){
        this.name = name;
    }

    //Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getTestScore() {
        return testScore;
    }
    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setTestScore(Double testScore) {
        this.testScore = testScore;
    }

    @Override
    public String toString() {
        return "Student [" +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Test Score=" + testScore +
                ']';
    }

    @Override
    public int compareTo(Student o) {
        return 0;
    }
}
