package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "grammar", schema = "test", catalog = "")
public class GrammarEntity {
    private int grammarId;
    private String form;
    private String lang;
    private String description;
    private String tag;

    @Id
    @Column(name = "grammar_id", nullable = false)
    public int getGrammarId() {
        return grammarId;
    }

    public void setGrammarId(int grammarId) {
        this.grammarId = grammarId;
    }

    @Basic
    @Column(name = "form", nullable = false, length = 255)
    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Basic
    @Column(name = "lang", nullable = false, length = 3)
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "tag", nullable = false, length = 255)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GrammarEntity that = (GrammarEntity) o;

        if (grammarId != that.grammarId) return false;
        if (form != null ? !form.equals(that.form) : that.form != null) return false;
        if (lang != null ? !lang.equals(that.lang) : that.lang != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = grammarId;
        result = 31 * result + (form != null ? form.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
