package com.panda.teller.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 16-11-27.
 */

public class FileUtil {

    public static File createFile(String dir, String fileName) {
        File file = new File(dir, fileName);
        try {
            if(file.exists()) {
                /* 如果已存在则删除 */
                file.delete();
            }
            file.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static File createFile(File parent, String fileName) {
        return FileUtil.createFile(parent.getPath(), fileName);
    }

}
