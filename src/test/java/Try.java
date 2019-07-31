import com.lottery.MainBootStart;
import com.lottery.repository.CustomerRepository;
import com.lottery.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MainBootStart.class)
public class Try {
    @Autowired
    private CustomerRepository customerDao;
    @Test
    public  void  testSave(){//默认 增
        Customer C=new Customer();
        C.setCustName("嗡嗡嗡奥赛德");
        Customer one = customerDao.save(C);
    }
    @Test
    public  void  testDel(){//默认 删
        Customer C=new Customer();
        C.setCustName("嗡嗡嗡");
        customerDao.deleteById(13L);
    }
    @Test
    public  void  testUpdata(){//默认 改
        //更新:先查后改
        Customer C =customerDao.getOne(14L);//默认 查
        C.setCustName("企鹅答答");
        customerDao.save(C);
    }

    @Test
    public  void  testQury(){//jpql
       List<Customer> lc=customerDao.findSome();
        for (Customer customer : lc) {
            System.out.println(customer);
        }
    }
    @Test
    public  void  testQuryBy(){
        List<Customer> lc=customerDao.findSomeBy("%嗡%");
        for (Customer customer : lc) {
            System.out.println(customer);
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public  void  testQuryModify(){
        customerDao.upSome("嗡嗡嗡啦",15L);
    }

    @Test
    public  void  testfindSql(){
        List<Customer> lc=customerDao.findSql();
        for (Customer customer : lc) {
            System.out.println(customer);
        }
    }
    @Test
    public  void  testMMGZ(){//命名规则
        Customer lc=customerDao.findByCustNameAndCustAddressEquals("企鹅答答","全球");
            System.out.println(lc);
    }
}
