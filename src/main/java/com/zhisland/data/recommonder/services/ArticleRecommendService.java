/**
 * 
 */
package com.zhisland.data.recommonder.services;

import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;

/**
 * 文章推荐服务
 * 
 * @author muzongyan
 *
 */
public interface ArticleRecommendService {

    public Set<Integer> recommend(final int id, final int start, final int rows) throws SolrServerException;

}
