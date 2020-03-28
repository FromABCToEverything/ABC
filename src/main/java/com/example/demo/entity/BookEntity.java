package com.example.demo.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "book", schema = "test", catalog = "")
public class BookEntity {
    private int bookId;
    private String title;
    private String author;
    private String language;
    private String coverUrl;
    private String textUrl;
    private String description;
    private String isbn;
    private String press;
    private Date publicatedDate;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "language", nullable = false, length = 3)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    @Column(name = "cover_url", nullable = false, length = 255)
    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Basic
    @Column(name = "text_url", nullable = false, length = 255)
    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 15)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "press", nullable = false, length = 255)
    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    @Basic
    @Column(name = "publicated_date", nullable = false)
    public Date getPublicatedDate() {
        return publicatedDate;
    }

    public void setPublicatedDate(Date publicatedDate) {
        this.publicatedDate = publicatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (language != null ? !language.equals(that.language) : that.language != null) return false;
        if (coverUrl != null ? !coverUrl.equals(that.coverUrl) : that.coverUrl != null) return false;
        if (textUrl != null ? !textUrl.equals(that.textUrl) : that.textUrl != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (press != null ? !press.equals(that.press) : that.press != null) return false;
        if (publicatedDate != null ? !publicatedDate.equals(that.publicatedDate) : that.publicatedDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (coverUrl != null ? coverUrl.hashCode() : 0);
        result = 31 * result + (textUrl != null ? textUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (press != null ? press.hashCode() : 0);
        result = 31 * result + (publicatedDate != null ? publicatedDate.hashCode() : 0);
        return result;
    }
}
