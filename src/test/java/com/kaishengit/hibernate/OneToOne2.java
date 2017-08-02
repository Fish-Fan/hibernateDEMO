package com.kaishengit.hibernate;

import com.kaishengit.pojo.Passage;
import com.kaishengit.pojo.PassageContent;
import org.junit.*;
import org.junit.Test;

/**
 * Created by yanfeng-mac on 2017/8/2.
 */
public class OneToOne2 extends BaseTest {

    @org.junit.Test
    public void save() {

        Passage passage = new Passage();
        passage.setTitle("小说A");

        PassageContent passageContent = new PassageContent();
        passageContent.setContent("小说A的内容");

        passage.setPassageContent(passageContent);
        passageContent.setPassage(passage);


        session.save(passage);
        session.save(passageContent);


    }

    @Test
    public void find() {
        //从从到主的单向外键查询
//        Passage passage = (Passage) session.get(Passage.class,10);
//
//        System.out.println(passage.getTitle());
//
//        PassageContent passageContent = passage.getPassageContent();
//
//        System.out.println(passageContent.getContent());

        //从主到从的单向外键查询
        //无延时加载
//        PassageContent passageContent1 = (PassageContent) session.get(PassageContent.class,7);
//        System.out.println(passageContent1.getContent());
//        Passage passage1 = passageContent1.getPassage();
//        System.out.println(passage1.getTitle());
    }
}
