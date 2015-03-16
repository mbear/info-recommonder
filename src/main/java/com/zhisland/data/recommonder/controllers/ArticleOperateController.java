/**
 * 
 */
package com.zhisland.data.recommonder.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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
import com.zhisland.data.recommonder.services.ArticleOperateService;

/**
 * 文章操作web接口
 * 
 * @author muzongyan
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleOperateController {

    @Autowired
    private ArticleOperateService articleOperateService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String add(final int id) throws JsonGenerationException, JsonMappingException, IOException {
        JsonStruct struct = new JsonStruct();
        JsonData data = new JsonData();

        try {
            articleOperateService.add(id);
            data.put("result", "ok");
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

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final int id) throws JsonGenerationException, JsonMappingException, IOException {
        JsonStruct struct = new JsonStruct();
        JsonData data = new JsonData();

        try {
            articleOperateService.update(id);
            data.put("result", "ok");
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

    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String delete(final int id) throws JsonGenerationException, JsonMappingException, IOException {
        JsonStruct struct = new JsonStruct();
        JsonData data = new JsonData();

        try {
            articleOperateService.delete(id);
            data.put("result", "ok");
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
