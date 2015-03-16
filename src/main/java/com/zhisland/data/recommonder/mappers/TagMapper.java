/**
 * 
 */
package com.zhisland.data.recommonder.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Select;

/**
 * @author muzongyan
 *
 */
public interface TagMapper {

    @Select("SELECT t.tag_name FROM ts_tag_relation AS r LEFT OUTER JOIN ts_tag AS t ON t.tag_id = r.tag_id WHERE r.relation_id = #{id}")
    public List<String> selectTagsByArticleId(final int id);
}
