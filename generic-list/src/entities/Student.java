package entities;

public class Student implements Comparable<Student> {
    private String name;
    private Integer id;
    private Double score;

    public Student(String name, Integer id, Double score){
        this.name = name;
        this.id = id;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.getId());
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof Student){
            return this.id.equals(((Student) other).getId());
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", id=" + id + ", score=" + score + "]";
    }
}
