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
//        passageContent.setPassage(passage);


        session.save(passageContent);
        session.save(passage);



    }

    @Test
    public void find() {
        //从多向一查询
        //有延时加载
        Passage passage = (Passage) session.get(Passage.class,13);

        System.out.println(passage.getTitle());

        PassageContent passageContent = passage.getPassageContent();

        System.out.println(passageContent.getContent());

        //从一向多查询
        //无延时加载
        PassageContent passageContent1 = (PassageContent) session.get(PassageContent.class,11);
        System.out.println(passageContent1.getContent());
//        Passage passage1 = passageContent1.getPassage();
//        System.out.println(passage1.getTitle());
    }
}
