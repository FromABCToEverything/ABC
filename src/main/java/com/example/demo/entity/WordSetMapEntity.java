package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "word_set_map", schema = "test", catalog = "")
public class WordSetMapEntity {
    private int setId;
    private int entryId;
    private int number;
    @Id
    @Basic
    @Column(name = "set_id", nullable = false)
    public int getSetId() {
        return setId;
    }

    public void setSetId(int setId) {
        this.setId = setId;
    }

    @Basic
    @Column(name = "entry_id", nullable = false)
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    @Basic
    @Column(name = "number", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordSetMapEntity that = (WordSetMapEntity) o;

        if (setId != that.setId) return false;
        if (entryId != that.entryId) return false;
        if (number != that.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setId;
        result = 31 * result + entryId;
        result = 31 * result + number;
        return result;
    }
}
