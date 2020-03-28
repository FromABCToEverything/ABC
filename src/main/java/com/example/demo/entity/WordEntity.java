package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "word", schema = "test", catalog = "")
public class WordEntity {
    private int wordId;
    private String content;
    private String pos;
    private String lang;
    private String meaningRaw;
    private String meaningZh;
    private String pronounceUrl;
    private String pronounce;
    private String tag;

    @Id
    @Column(name = "word_id", nullable = false)
    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "pos", nullable = false, length = 255)
    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
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
    @Column(name = "meaning_raw", nullable = false, length = 8191)
    public String getMeaningRaw() {
        return meaningRaw;
    }

    public void setMeaningRaw(String meaningRaw) {
        this.meaningRaw = meaningRaw;
    }

    @Basic
    @Column(name = "meaning_zh", nullable = false, length = 255)
    public String getMeaningZh() {
        return meaningZh;
    }

    public void setMeaningZh(String meaningZh) {
        this.meaningZh = meaningZh;
    }

    @Basic
    @Column(name = "pronounce_url", nullable = false, length = 1023)
    public String getPronounceUrl() {
        return pronounceUrl;
    }

    public void setPronounceUrl(String pronounceUrl) {
        this.pronounceUrl = pronounceUrl;
    }

    @Basic
    @Column(name = "pronounce", nullable = false, length = 1023)
    public String getPronounce() {
        return pronounce;
    }

    public void setPronounce(String pronounce) {
        this.pronounce = pronounce;
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

        WordEntity that = (WordEntity) o;

        if (wordId != that.wordId) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (pos != null ? !pos.equals(that.pos) : that.pos != null) return false;
        if (lang != null ? !lang.equals(that.lang) : that.lang != null) return false;
        if (meaningRaw != null ? !meaningRaw.equals(that.meaningRaw) : that.meaningRaw != null) return false;
        if (meaningZh != null ? !meaningZh.equals(that.meaningZh) : that.meaningZh != null) return false;
        if (pronounceUrl != null ? !pronounceUrl.equals(that.pronounceUrl) : that.pronounceUrl != null) return false;
        if (pronounce != null ? !pronounce.equals(that.pronounce) : that.pronounce != null) return false;
        if (tag != null ? !tag.equals(that.tag) : that.tag != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (meaningRaw != null ? meaningRaw.hashCode() : 0);
        result = 31 * result + (meaningZh != null ? meaningZh.hashCode() : 0);
        result = 31 * result + (pronounceUrl != null ? pronounceUrl.hashCode() : 0);
        result = 31 * result + (pronounce != null ? pronounce.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }
}
