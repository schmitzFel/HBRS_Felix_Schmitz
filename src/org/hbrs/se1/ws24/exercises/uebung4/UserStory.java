package org.hbrs.se1.ws24.exercises.uebung4;

import java.io.Serializable;

public class UserStory implements Serializable {
    private Integer id;
    private String epic;
    private String description;
    private String acceptanceCriteria;
    private int effort;
    private int value;

    private int penalty;

    private int risk;


    // Konstruktor, Getter, und Setter
    public UserStory(Integer id, String epic, String description, String acceptanceCriteria, int effort, int value, int penalty, int risk) {
        this.id = id;
        this.epic = epic;
        this.description = description;
        this.acceptanceCriteria = acceptanceCriteria;
        this.effort = effort;
        this.value = value;
        this.penalty = penalty;
        this.risk = risk;

    }

    public Integer getID() {
        return this.id;
    }

    public int getPriority() {
        return (this.value + this.penalty) / (this.effort + this.risk);
    }

    public int getEffort() {
        return effort;
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
                ", Priority=" + getPriority() +
                ", Effort=" + effort +
                ", Value=" + value +
                ", Penalty=" + penalty +
                ", Risk=" + risk +
                '}';
    }

}
