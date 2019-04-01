package com.xuanluo.myshop.statics.backend.sitemesh.tagrule;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

/**
 * 自定义标签
 */
public class MyTagRuleBundle implements TagRuleBundle {

    private final static String MYCSS="myCss";
    private final static String MYBODY="myBody";
    private final static String MYJS="myJs";
    private final static String MYSCRIPT="myScript";
    private final static String TIPS="tips";

    @Override
    public void install(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        insertRule(state, contentProperty, siteMeshContext,MYBODY);
        insertRule(state, contentProperty, siteMeshContext,MYCSS);
        insertRule(state, contentProperty, siteMeshContext,MYJS);
        insertRule(state, contentProperty, siteMeshContext,MYSCRIPT);
        insertRule(state, contentProperty, siteMeshContext,TIPS);
    }

    @Override
    public void cleanUp(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
        getChild(contentProperty, MYBODY);
        getChild(contentProperty, MYCSS);
        getChild(contentProperty, MYJS);
        getChild(contentProperty, MYSCRIPT);
        getChild(contentProperty, TIPS);
    }

    /**
     * 添加自定义标签
     * @param tagName 标签名
     * @param state
     * @param contentProperty
     * @param siteMeshContext
     */
    private void insertRule(State state, ContentProperty contentProperty, SiteMeshContext siteMeshContext,String tagName) {
        state.addRule(tagName, new ExportTagToContentRule(siteMeshContext,contentProperty.getChild(tagName),false));
    }

    /**
     *
     * @param contentProperty
     * @param tagName 标签名
     */
    private void getChild(ContentProperty contentProperty, String tagName) {
        if (!((ContentProperty) contentProperty.getChild(tagName)).hasValue()) {
            ((ContentProperty) contentProperty.getChild(tagName)).setValue(contentProperty.getValue());
        }
    }
}
