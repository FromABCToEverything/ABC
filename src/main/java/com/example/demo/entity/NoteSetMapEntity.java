package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "note_set_map", schema = "test", catalog = "")
public class NoteSetMapEntity {
    private int setId;
    private int entryId;
    private int order;
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
    @Column(name = "_order", nullable = false)
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteSetMapEntity that = (NoteSetMapEntity) o;

        if (setId != that.setId) return false;
        if (entryId != that.entryId) return false;
        if (order != that.order) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = setId;
        result = 31 * result + entryId;
        result = 31 * result + order;
        return result;
    }
}
