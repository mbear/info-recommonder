/**
 * 
 */
package com.zhisland.data.recommonder.services.solr;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MoreLikeThisParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhisland.data.recommonder.dtos.Article;
import com.zhisland.data.recommonder.utils.Constants;

/**
 * @author muzongyan
 *
 */
@Service
public class ArticleSolrServiceImpl implements ArticleSolrService {

    @Value("${article.id}")
    private String field_id;

    @Value("${article.title}")
    private String field_title;

    @Value("${article.summary}")
    private String field_summary;

    @Value("${article.body}")
    private String field_body;

    @Value("${article.tag}")
    private String field_tag;

    @Value("${article.author}")
    private String field_author;

    @Value("${article.public_date}")
    private String field_public_date;

    @Value("${article.title.boost}")
    private float field_boost_title;

    @Value("${article.summary.boost}")
    private float field_boost_summary;

    @Value("${article.body.boost}")
    private float field_boost_body;

    @Value("${article.author.boost}")
    private float field_boost_author;

    @Value("${article.tag.boost}")
    private float field_boost_tag;

    @Autowired
    private SolrServer solrServer;

    @Override
    public void add(Article article) throws SolrServerException, IOException {

        SolrInputDocument doc = new SolrInputDocument();
        doc.setField(field_id, article.getId());
        doc.setField(field_public_date, article.getPublicDate());
        
//        doc.setField(field_title, article.getTitle(), field_boost_title);
//        doc.setField(field_summary, article.getSummary(), field_boost_summary);
//        doc.setField(field_body, article.getBody(), field_boost_body);
        
        doc.setField(field_title, article.getTitle());
        doc.setField(field_summary, article.getSummary());
        doc.setField(field_body, article.getBody());
        

        for (String tag : article.getTags()) {
//            doc.addField(field_tag, tag, field_boost_tag);
            doc.addField(field_tag, tag);
        }

        for (String author : article.getAuthors()) {
//            doc.addField(field_author, author, field_boost_author);
            doc.addField(field_author, author);
        }

        solrServer.add(doc);

        solrServer.commit(true, true, true);
    }

    @Override
    public void delete(int id) throws SolrServerException, IOException {
        solrServer.deleteById(String.valueOf(id));

        solrServer.commit(true, true, true);
    }

    @Override
    public Set<Integer> recommend(int id, int start, int rows) throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();

        // 使用“more like this”requestHandler 进行推荐
        solrQuery.setRequestHandler("/" + MoreLikeThisParams.MLT);

        // 匹配文档不包括源文档
        solrQuery.set(MoreLikeThisParams.MATCH_INCLUDE, false);

        solrQuery.set(MoreLikeThisParams.MIN_DOC_FREQ, 1);

        solrQuery.set(MoreLikeThisParams.MIN_TERM_FREQ, 1);

        solrQuery.set(MoreLikeThisParams.MIN_WORD_LEN, 2);

        solrQuery.set(MoreLikeThisParams.MAX_QUERY_TERMS, 100);

        // request interesting terms to be booted with their tf-idf score
        solrQuery.set(MoreLikeThisParams.BOOST, true);

        // request interesting terms to be returned along with documents
         solrQuery.set(MoreLikeThisParams.INTERESTING_TERMS, "details");

        // the fields in which to look for interesting terms
        solrQuery.set(MoreLikeThisParams.SIMILARITY_FIELDS, field_tag + Constants.COMMA + field_summary
                + Constants.COMMA + field_author + Constants.COMMA + field_title + Constants.COMMA + field_body);

        // boosting applied to mlt fields
        solrQuery.set(MoreLikeThisParams.QF, field_author + "^" + field_boost_author + " " + field_body + "^"
                + field_boost_body + " " + field_summary + "^" + field_boost_summary + " " + field_tag + "^"
                + field_boost_tag + " " + field_title + "^" + field_boost_title);

        // return list of fields
        solrQuery.set("fl", "id, score");

        // default search field
        solrQuery.set("df", "text");

        // set query condition
        // based on document id
        solrQuery.setQuery("id:" + id);

        // set pagination
        solrQuery.setStart(start);
        solrQuery.setRows(rows);

        QueryResponse resp = solrServer.query(solrQuery);
        SolrDocumentList hits = resp.getResults();

        Set<Integer> rmds = new LinkedHashSet<Integer>();
        for (SolrDocument doc : hits) {
            rmds.add(Integer.parseInt(doc.getFieldValue(field_id).toString()));
        }

        return rmds;
    }

}
