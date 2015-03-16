/**
 * 
 */
package com.zhisland.data.recommonder.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisland.data.recommonder.dtos.Article;
import com.zhisland.data.recommonder.mappers.ArticleMapper;
import com.zhisland.data.recommonder.mappers.TagMapper;
import com.zhisland.data.recommonder.services.solr.ArticleSolrService;

/**
 * @author muzongyan
 *
 */
@Service
public class ArticleOperateServiceImpl implements ArticleOperateService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleSolrService articleSolrService;

    @Override
    public void add(int id) throws SolrServerException, IOException {
        Article article = new Article();

        // 通过文章id从 app mysql 数据库中取得文章的标题、摘要、正文、作者、发布时间
        article = articleMapper.selectArticleById(id);

        // 通过文章id从 app mysql 数据库中取得文章的标签
        List<String> tags = tagMapper.selectTagsByArticleId(id);
        article.setTags(tags);

        // 处理author字段
        List<String> authors = new ArrayList<String>();
        for (String author : StringUtils.split(article.getAuthor())) {
            authors.add(author);
        }
        article.setAuthors(authors);

        // html标签处理
        article.setSummary(StringEscapeUtils.unescapeHtml(article.getSummary()));
        article.setBody(StringEscapeUtils.unescapeHtml(article.getBody()));

        article.setId(id);

        // 在solr索引中添加文档
        articleSolrService.add(article);
    }

    @Override
    public void update(int id) throws SolrServerException, IOException {
        Article article = new Article();

        // 通过文章id从 app mysql 数据库中取得文章的标题、摘要、正文、作者、发布时间
        article = articleMapper.selectArticleById(id);

        // 通过文章id从 app mysql 数据库中取得文章的标签
        List<String> tags = tagMapper.selectTagsByArticleId(id);
        article.setTags(tags);

        // 处理author字段
        List<String> authors = new ArrayList<String>();
        for (String author : StringUtils.split(article.getAuthor())) {
            authors.add(author);
        }
        article.setAuthors(authors);

        // html标签处理
        article.setSummary(StringEscapeUtils.unescapeHtml(article.getSummary()));
        article.setBody(StringEscapeUtils.unescapeHtml(article.getBody()));

        article.setId(id);

        // 在solr索引中添加文档
        articleSolrService.add(article);
    }

    @Override
    public void delete(int id) throws SolrServerException, IOException {
        articleSolrService.delete(id);
    }

}
