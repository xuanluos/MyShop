package com.xuanluo.myshop.service.content.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xuanluo.myshop.commons.domain.TbContent;
import com.xuanluo.myshop.commons.dto.DatatableDTO;
import com.xuanluo.myshop.commons.model.AbstractBaseController;
import com.xuanluo.myshop.commons.validator.Utils.MapperUtils;
import com.xuanluo.myshop.service.redis.api.RedisService;
import com.xuanluo.myshop.service.content.api.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "content")
public class TbContentController extends AbstractBaseController<TbContent> {

    /**
     * 调用用户服务
     */
    @Reference(version = "${services.versions.content.v1}")
    private TbContentService tbContentService;

    @Reference(version = "${services.versions.redis.v1}")
    private RedisService redisService;

    @ModelAttribute(value = "TbContent")
    public TbContent get(@RequestParam(required = false) Long id) throws InstantiationException, IllegalAccessException {
        return (TbContent) getById(tbContentService,id);
    }

    @RequestMapping(value = "form")
    public String form(){
        return "content/form";
    }

    @PostMapping(value = "save")
    public String save(TbContent tbContent,  RedirectAttributes redirectAttributes){
        if(beanValidator(tbContent,redirectAttributes)){
            tbContentService.save(tbContent);
            addMessage(redirectAttributes,"添加成功");
            return "redirect:/content/list";
        }
        return "redirect:/content/form";
    }

    @RequestMapping(value = "delete")
    public String delete(TbContent tbContent){
        tbContentService.delete(tbContent);
        return "redirect:/content/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "content/list";
    }

    @ResponseBody
    @PostMapping(value = "page")
    public DatatableDTO<TbContent> page(TbContent tbContent, HttpServletRequest request){
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String key=String.format("redis-%s-%s-%s",strDraw,strLength,strStart);
        String value = (String) redisService.get(key);
        DatatableDTO<TbContent> datatableDTO=null;
        //放进缓存
        if(value == null){
            datatableDTO=page(tbContent,tbContentService,request);
            try {
                value=MapperUtils.obj2json(datatableDTO);
                redisService.put(key,value,300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //从缓存中取出
        else {
            try {
                datatableDTO = MapperUtils.json2pojo(value, DatatableDTO.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return datatableDTO;
    }
}
