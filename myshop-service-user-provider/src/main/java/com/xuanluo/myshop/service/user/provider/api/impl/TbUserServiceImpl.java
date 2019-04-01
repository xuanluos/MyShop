package com.xuanluo.myshop.service.user.provider.api.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xuanluo.myshop.commons.domain.TbUser;
import com.xuanluo.myshop.commons.mapper.TbUserMapper;
import com.xuanluo.myshop.commons.model.impl.BaseCrudServiceImpl;
import com.xuanluo.myshop.service.user.api.TbUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service(version = "${services.versions.user.v1}")
@Transactional(readOnly = true)
public class TbUserServiceImpl extends BaseCrudServiceImpl<TbUser,TbUserMapper> implements TbUserService {


    @Override
    public List<TbUser> selectAll() {
        return  mapper.selectAll();
    }

    /**
     * 分页查询
     * @param tbUser
     * @param offset 数据起始位置
     * @param limit  每页显示笔数
     * @return
     */
    @Override
    public PageInfo<TbUser> page(TbUser tbUser, int offset, int limit) {
        Example example = new Example(TbUser.class);
        Example.Criteria criteria = example.createCriteria();
        example.orderBy("updated").desc();

        fuzzyQuery(criteria,  "username",tbUser.getUsername());
        fuzzyQuery(criteria, "phone", tbUser.getPhone());
        fuzzyQuery(criteria, "email", tbUser.getEmail());

        PageHelper.offsetPage(offset,limit);
        PageInfo<TbUser> pageInfo=new PageInfo<TbUser>(mapper.selectByExample(example));
        return pageInfo;
    }


    @Override
    @Transactional(readOnly = false)
    public void save(TbUser tbUser) {
        if(tbUser.preSave()){
            //新增用户
            tbUser.setPassword(DigestUtils.md5Hex(tbUser.getPassword().getBytes()));
            mapper.insert(tbUser);
        }else {
            //编辑用户
            if(StringUtils.isNotBlank(tbUser.getPassword())){
                tbUser.setPassword(DigestUtils.md5Hex(tbUser.getPassword().getBytes()));
            }else {
                tbUser.setPassword(getById(tbUser.getId()).getPassword());
            }
            mapper.updateByPrimaryKey(tbUser);
        }

    }

    /**
     * 模糊查询
     * @param criteria
     * @param attributes  查询的属性
     * @param value  查询的值
     */
    private void fuzzyQuery(Example.Criteria criteria, String attributes, String value) {
        if (StringUtils.isNotBlank(value)) {
            criteria.andLike(attributes, "%" + value + "%");
        }
    }


}
