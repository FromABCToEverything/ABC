package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "word_count", schema = "test", catalog = "")
public class WordCountEntity {
    private Integer wordId;
    private Integer userId;
    private Integer bookId;
    @Id
    @Basic
    @Column(name = "word_id", nullable = true)
    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "book_id", nullable = true)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WordCountEntity that = (WordCountEntity) o;

        if (wordId != null ? !wordId.equals(that.wordId) : that.wordId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (bookId != null ? !bookId.equals(that.bookId) : that.bookId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordId != null ? wordId.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        return result;
    }
}
