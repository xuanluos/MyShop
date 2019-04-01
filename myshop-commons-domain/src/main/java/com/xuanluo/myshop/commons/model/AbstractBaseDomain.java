package com.xuanluo.myshop.commons.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public abstract class AbstractBaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created;

    private Date updated;

    /**
     * 保存之前判断
     * @return false为修改
     *         true为新增
     */
    public Boolean preSave(){
        this.updated=new Date();
        if(this.id==null){
            this.created=new Date();
            return true;
        }
        return false;
    }
}
