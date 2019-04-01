package com.xuanluo.myshop.statics.backend.sitemesh.filter;


import com.xuanluo.myshop.statics.backend.sitemesh.tagrule.MyTagRuleBundle;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * sitemesh拦截规则
 */
public class Meshsite3Filter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        //拦截规则，/decorator/default 会被转发
        builder.addDecoratorPath("/*", "/decorator/default")
                //白名单
                .addExcludedPath("/static/**")
                //自定义标签
                .addTagRuleBundle(new MyTagRuleBundle())
        ;
    }
}
