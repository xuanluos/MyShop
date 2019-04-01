package com.xuanluo.myshop.commons.model;

import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.dto.DatatableDTO;
import com.xuanluo.myshop.commons.validator.BeanValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractBaseController<T extends AbstractBaseDomain>  {

    private final  static String MESSAGE="message";

    private ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
    private Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];

    public boolean beanValidator (T t, Model model){
        return checkValidator(t,model);
    }

    public boolean beanValidator (T t, RedirectAttributes redirectAttributes){
        return checkValidator(t,redirectAttributes);
    }

    /**
     * 验证
     * @param t
     * @param model
     * @return true 成功
     *              失败
     */
    private boolean checkValidator(T t, Model model) {
        String validator = BeanValidator.validator(t);
        if(StringUtils.isNotBlank(validator)){
            if(model instanceof RedirectAttributes){
                //添加重定向消息
                RedirectAttributes redirectAttributes = (RedirectAttributes) model;
                addMessage(redirectAttributes, validator);
            }else {
                //添加转发消息
               addMessage(model,validator);
            }
            return false;
        }
        return true;
    }

    /**
     * 添加重定向消息
     * @param redirectAttributes
     * @param message
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String message) {
        redirectAttributes.addFlashAttribute("MESSAGE",message);
    }

    /**
     * 添加转发消息
     * @param model
     * @param message
     */
    protected void addMessage(Model model, String message) {
        model.addAttribute("MESSAGE",message);
    }

    protected T getById(BaseCrudService<T> service, Long id) throws IllegalAccessException, InstantiationException {
        // ID 为空则返回一个新的实例
        if (id == null) {
            return (T) clazz.newInstance();
        }

        // 根据 ID 查询实例
        else {
            return service.getById(id);
        }
    }

    protected DatatableDTO<T> page(T entity, BaseCrudService<T> service, HttpServletRequest request) {
        DatatableDTO<T> dataTable = new DatatableDTO<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");

        PageInfo<T> pageInfo = service.page(entity, Integer.parseInt(strStart), Integer.parseInt(strLength));
        dataTable.setDraw(Integer.parseInt(strDraw));
        dataTable.setRecordsTotal(pageInfo.getTotal());
        dataTable.setRecordsFiltered(pageInfo.getTotal());
        dataTable.setData(pageInfo.getList());

        return dataTable;
    }
}
