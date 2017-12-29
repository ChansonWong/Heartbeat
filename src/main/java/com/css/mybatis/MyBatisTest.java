package com.css.mybatis;

import com.css.entity.TRequestRecord;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class MyBatisTest {

    public static void main(String[] args) throws IOException {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            TRequestRecord record = session.selectOne("TRequestRecordMapper.selectRecord", '1');
            System.out.println("查询出的结果：" + record.toString());
        }finally {
            session.close();
        }
    }
}
