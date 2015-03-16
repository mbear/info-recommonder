/**
 * 
 */
package com.zhisland.data.recommonder.services.solr;

import java.io.IOException;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;

import com.zhisland.data.recommonder.dtos.Article;

/**
 * @author muzongyan
 *
 */
public interface ArticleSolrService {

    public void add(Article article) throws SolrServerException, IOException;

    public void delete(int id) throws SolrServerException, IOException;

    public Set<Integer> recommend(int id, int start, int rows) throws SolrServerException;
}
