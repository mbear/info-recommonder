/**
 * 
 */
package com.zhisland.data.recommonder.mappers;

import org.apache.ibatis.annotations.Select;

import com.zhisland.data.recommonder.dtos.Article;

/**
 * @author muzongyan
 *
 */
public interface ArticleMapper {

    @Select("SELECT a.title AS title, a.summary AS summary, a.body AS body, a.author AS author, a.publish_date AS publicDate"
            + " FROM ts_article_articles AS a WHERE a.article_id = #{id}")
    public Article selectArticleById(final int id);
}
