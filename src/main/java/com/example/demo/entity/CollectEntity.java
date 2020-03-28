package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "collect", schema = "test", catalog = "")
public class CollectEntity {
    private String collectorId;
    private String type;
    private int entryId;
    @Id
    @Basic
    @Column(name = "collector_id", nullable = false, length = 255)
    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    @Basic
    @Column(name = "type", nullable = false, length = 1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "entry_id", nullable = false)
    public int getEntryId() {
        return entryId;
    }

    public void setEntryId(int entryId) {
        this.entryId = entryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CollectEntity that = (CollectEntity) o;

        if (entryId != that.entryId) return false;
        if (collectorId != null ? !collectorId.equals(that.collectorId) : that.collectorId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = collectorId != null ? collectorId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + entryId;
        return result;
    }
}
