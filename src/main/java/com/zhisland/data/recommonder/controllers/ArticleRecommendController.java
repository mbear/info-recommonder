/**
 * 
 */
package com.zhisland.data.recommonder.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhisland.data.recommonder.jsend.JsonData;
import com.zhisland.data.recommonder.jsend.JsonStruct;
import com.zhisland.data.recommonder.services.ArticleRecommendService;

/**
 * 文章推荐web接口
 * 
 * @author muzongyan
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleRecommendController {

    @Autowired
    private ArticleRecommendService articleRecommendService;

    @ResponseBody
    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(final int id, final int start, final int rows) throws JsonGenerationException,
            JsonMappingException, IOException {
        JsonStruct struct = new JsonStruct();
        JsonData data = new JsonData();

        try {
            Set<Integer> rmdIds = articleRecommendService.recommend(id, start, rows);
            data.put("result", rmdIds);
            struct.setData(data);
            struct.setStatusToSuccess();
        } catch (Exception e) {
            struct.setMessage(e.getMessage());
            struct.setStatusToError();

            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        mapper.writeValue(out, struct);

        return out.toString("UTF-8");
    }

}
