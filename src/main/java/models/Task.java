package models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime due;
    private Integer userId;

    public Task() {
    }

    public Task(String title, String description, LocalDateTime due) {
        this.title = title;
        this.description = description;
        this.due = due;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDue() {
        return due;
    }

    public void setDue(LocalDateTime due) {
        this.due = due;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(due, task.due);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, due);
    }
}
