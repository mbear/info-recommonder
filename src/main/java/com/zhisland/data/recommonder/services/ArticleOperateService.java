/**
 * 
 */
package com.zhisland.data.recommonder.services;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

/**
 * 文章操作服务
 * 
 * @author muzongyan
 *
 */
public interface ArticleOperateService {

    public void add(final int id) throws SolrServerException, IOException;

    public void update(final int id) throws SolrServerException, IOException;

    public void delete(final int id) throws SolrServerException, IOException;
}
