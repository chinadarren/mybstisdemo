import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.Reader;

/**
 * Created by lei.chen on 5/5/2015.
 */
public class Test {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

 static {
    try {
        reader  = Resources.getResourceAsReader("Configuration.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }catch (Exception e){
        e.printStackTrace();
    }
}

    public static SqlSessionFactory getSqlSessionFactory(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            User user=(User)session.selectOne("UserMapper.selectUserByID", 3);

            System.out.println(user.getUserAddress());
            System.out.println(user.getUserName());
        }finally {
            session.close();
        }
    }

}
