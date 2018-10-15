package com.util.gen;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class GeneratorSqlmap {
	 public void generator() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
      //指定 逆向工程配置文件
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(new ProgressCallback() {
			@Override
			public void startTask(String arg0) {
				// TODO Auto-generated method stub
				System.out.println("-------startTask-------");
			}
			@Override
			public void saveStarted(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("-------saveStarted-------"+arg0);
			}
			@Override
			public void introspectionStarted(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("-------introspectionStarted-------"+arg0);
			}
			@Override
			public void generationStarted(int arg0) {
				// TODO Auto-generated method stub
				System.out.println("-------introspectionStarted-------"+arg0);
				
			}
			@Override
			public void done() {
				// TODO Auto-generated method stub
			}
			@Override
			public void checkCancel() throws InterruptedException {
				// TODO Auto-generated method stub
				
			}
		});

    } 
    public static void main(String[] args) throws Exception {
        try {
            GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
