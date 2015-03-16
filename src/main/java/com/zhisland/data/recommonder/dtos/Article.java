/**
 * 
 */
package com.zhisland.data.recommonder.dtos;

import java.util.Date;
import java.util.List;

/**
 * 资讯文章
 * 
 * @author muzongyan
 *
 */
public class Article {

    private int id;

    // 标题
    private String title;

    // 摘要
    private String summary;

    // 正文
    private String body;

    // 标签
    private List<String> tags;

    // 作者
    private List<String> authors;

    // 作者
    private String author;

    // 发布日期
    private Date publicDate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     *            the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * @return the tags
     */
    public List<String> getTags() {
        return tags;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return the authors
     */
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * @param authors
     *            the authors to set
     */
    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     *            the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the publicDate
     */
    public Date getPublicDate() {
        return publicDate;
    }

    /**
     * @param publicDate
     *            the publicDate to set
     */
    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

}
