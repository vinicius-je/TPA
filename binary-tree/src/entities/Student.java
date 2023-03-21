package entities;

public class Student {
    private Integer id;
    private String name;
    private Double testScore;

    public Student(Integer id, String name, Double testScore){
        this.id = id;
        this.name = name;
        this.testScore = testScore;
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
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", testScore=" + testScore +
                '}';
    }
}
