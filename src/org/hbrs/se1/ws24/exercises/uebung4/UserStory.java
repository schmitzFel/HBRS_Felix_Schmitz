package org.hbrs.se1.ws24.exercises.uebung4;

public class UserStory {
    private Integer id;
    private String epic;
    private String description;
    private String acceptanceCriteria;
    private int priority;
    private int velocity;


    // Konstruktor, Getter, und Setter
    public UserStory(Integer id, String epic, String description, String acceptanceCriteria, int priority, int velocity) {
        this.id = id;
        this.epic = epic;
        this.description=description;
        this.acceptanceCriteria = acceptanceCriteria;
        this.priority = priority;
        this.velocity = velocity;

    }

    public Integer getID(){
        return this.id;
    }

    public int getPriority() {
        return priority;
    }

    public int getVelocity() {
        return velocity;
    }

    public String getDescription() {
        return description;
    }

    public String getAcceptanceCriteria() {
        return acceptanceCriteria;
    }

    public String getEpic() {
        return epic;
    }

    @Override
    public String toString() {
        return "UserStory{" +
                "ID=" + id +
                ", Epic='" + epic + '\'' +
                ", Description='" + description + '\'' +
                ", Acceptance Criteria='" + acceptanceCriteria + '\'' +
                ", Priority=" + priority +
                ", Velocity=" + velocity +
                '}';
    }

}
