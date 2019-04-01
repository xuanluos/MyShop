package com.xuanluo.myshop.service.user.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.domain.TbUser;
import com.xuanluo.myshop.commons.dto.DatatableDTO;
import com.xuanluo.myshop.commons.model.AbstractBaseController;
import com.xuanluo.myshop.service.user.api.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "user")
public class TbUserController extends AbstractBaseController<TbUser> {

    @Reference(version = "${services.versions.user.v1}")
    private TbUserService tbUserService;

    @ModelAttribute(value = "tbUser")
    public TbUser get(@RequestParam(required = false) Long id) throws InstantiationException, IllegalAccessException {
        return (TbUser) getById(tbUserService,id);
    }

    @RequestMapping(value = "form")
    public String form(){
        return "user/form";
    }

    @RequestMapping(value = "save")
    public String save(TbUser tbUser,  RedirectAttributes redirectAttributes){
        if(beanValidator(tbUser,redirectAttributes)){
            tbUserService.save(tbUser);
            addMessage(redirectAttributes,"添加成功");
            return "redirect:/user/list";
        }
        return "redirect:/user/form";
    }

    @RequestMapping(value = "delete")
    public String delete(TbUser tbUser){
        tbUserService.delete(tbUser);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "user/list";
    }

    @ResponseBody
    @PostMapping(value = "page")
    public DatatableDTO<TbUser> page(TbUser tbUser, HttpServletRequest request){
        return page(tbUser,tbUserService,request);
    }
}
