package com.yjy.read.util;


import nl.siegmann.epublib.domain.*;
import nl.siegmann.epublib.epub.EpubReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EpubUtil {
    public static void main(String[] args) {
        // 直接获取文件的路径（相对路径） 对应项目根路径
        File file = new File("epub/三体.epub");
        // 打印绝对路径
        System.out.println(file.getAbsolutePath());
        InputStream in = null;
        try {
            EpubReader reader = new EpubReader();
            in = new FileInputStream(file);

            Book book = reader.readEpub(in);
            System.out.println(book.getTitle());

            // 书籍元数据
            Metadata metadata = book.getMetadata();
//            List<Author> author = metadata.getAuthors();
//            System.out.println(author.get(0).toString());

            Resources resources = book.getResources();
            // 所需要的资源数量
            System.out.println(resources.size());

            // 书籍的脊梁
            Spine spine = book.getSpine();
            System.out.println(spine.size());
            List<SpineReference> spineReferences = spine.getSpineReferences();
            for (SpineReference reference : spineReferences) {
                Resource resource = reference.getResource();
                System.out.println(resource.getHref());
                // 获取到资源  可能是字体 样式 图片 或 文本信息
//                MediaType type = resource.getMediaType();
//                System.out.println(type.getName());

                // 获取资源内容
//                resource.getData();
            }

        } catch (Exception e) {

        } finally {
            // 关闭输入输出流
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
