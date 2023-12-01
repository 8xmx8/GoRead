package com.yjy.read;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjy.read.entity.Author;
import com.yjy.read.entity.User;
import com.yjy.read.mapper.UserMapper;
import com.yjy.read.service.AuthorService;
import com.yjy.read.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TestApplication {

    @Autowired
    private AuthorService authorService;

    //    @Test
    public void test() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name", "说话");
        List<Author> list = authorService.list(wrapper);

        QueryWrapper wrapper2 = new QueryWrapper();
        wrapper2.notLike("name", "说话");
        List<Author> list2 = authorService.list(wrapper2);
        System.out.println(list2.size());

        QueryWrapper wrapper3 = new QueryWrapper();
        wrapper3.likeLeft("name", "肘子");
        List<Author> list3 = authorService.list(wrapper3);
        System.out.println(list3.size());

        QueryWrapper wrapper4 = new QueryWrapper();
        wrapper4.likeRight("name", "会");
        List<Author> list4 = authorService.list(wrapper4);
        System.out.println(list4.size());
    }

    @Autowired
    private UserService userService;

    // @Test
    public void test1() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("name", "毛毛");
        List<Integer> list = new ArrayList<>();
        list.add(1);
        List<User> users = userService.listByIds(list);
        System.out.println(users);
    }

    @Test
    public void test0121() {
        QueryWrapper<User> queryWrapper = Wrappers.query();
        queryWrapper.eq("id", 1);
        User one = userService.getOne(queryWrapper);
        System.out.println(one);
    }


    //    @Test
    public void test2() {
        // SELECT COUNT(`name`),age FROM USER GROUP BY age;
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.groupBy("age");
        wrapper.select("COUNT(`name`) as cnt", "age");
        List<User> list = userService.list(wrapper);
        System.out.println(list.toString());
    }

    @Autowired
    private UserMapper userMapper;

    //    @Test
    public void testMapper() {
//        List<GroupBean> list = userMapper.selectGroup();
//        List<User> list1 = userMapper.selectByName("'dmc'"); // ${}
        List<User> list1 = userMapper.selectByName("dmc");
        System.out.println(list1);
    }

    @Test
    public void testPage() {
//        Page<User> userPage = new Page<>(1, 2);
        Page<User> userPage = new Page<>(2, 3);
        Page<User> page = userService.page(userPage);
        System.out.println(page == userPage);

        List<User> list = page.getRecords();
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println();
    }


}
