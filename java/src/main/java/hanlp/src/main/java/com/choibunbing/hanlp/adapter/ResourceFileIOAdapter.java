package com.choibunbing.hanlp.adapter;

import com.hankcs.hanlp.corpus.io.IIOAdapter;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

/**
 * 分布式时使用，
 * TODO 需要修改，判断路径，判断bin，判断资源
 */
public class ResourceFileIOAdapter implements IIOAdapter {
    @Override
    public InputStream open(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return new FileInputStream(resource.getFile());
    }

    @Override
    public OutputStream create(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return new FileOutputStream(resource.getFile());
    }
}
