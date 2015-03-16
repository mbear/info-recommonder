/**
 * 
 */
package com.zhisland.data.recommonder.services;

import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhisland.data.recommonder.services.solr.ArticleSolrService;

/**
 * @author muzongyan
 *
 */
@Service
public class ArticleRecommendServiceImpl implements ArticleRecommendService {

    @Autowired
    private ArticleSolrService articleSolrServie;

    @Override
    public Set<Integer> recommend(int id, int start, int rows) throws SolrServerException {
        return articleSolrServie.recommend(id, start, rows);
    }

}
