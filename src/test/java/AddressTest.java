import com.sun.playcat.dao.AddressDao;
import com.sun.playcat.domain.Address;
import com.sun.playcat.domain.AddressList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by sunlin on 2017/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class AddressTest {
    @Resource
    private AddressDao addressService;
    @Test
    public void insert()
    {
        Address address=new Address();
        address.setUser_id(10012);
        address.setName("阿三");
        address.setPhone("13588888888");
        address.setPost_code("00000");
        address.setCountry_id(86);
        address.setProvince_id(2);
        address.setCity_id(3);
        address.setDistrict_id(4);
        address.setArea_name("浙江 杭州 江干区");
        address.setAddress("九堡镇九盛路100号 东方电子商务园区 21幢 101室");
        address.setCreate_time(new Date());
        address.setStatus(1);
        addressService.insert(address);
        assert (address.getId()>0);

    }
    @Test
    public void search()
    {
        AddressList addressList=new AddressList();
        addressList.setStatus(1);
        addressList.setStart(0);
        addressList.setPageNum(1);
        addressList.setCount(1);
        addressList.setUser_id(10012);

        List<Address> dataList=addressService.search(
                addressList.getUser_id(),
                addressList.getStatus(),
                addressList.getStart(),
                addressList.getPageNum());
        addressList.setAddressList(dataList);

        assert (dataList.size()==1);
    }
    @Test
    public void update()
    {
        Address address=new Address();
        address.setId(2);
        address.setUser_id(10012);
        address.setName("阿四");
        address.setPhone("13588888888");
        address.setPost_code("111111");
        address.setCountry_id(86);
        address.setProvince_id(2);
        address.setCity_id(3);
        address.setDistrict_id(4);
        address.setArea_name("浙江 杭州 江干区");
        address.setAddress("江干区九堡镇九盛路100号 东方电子商务园区 21幢 101室");
        address.setCreate_time(new Date());
        address.setStatus(1);

        addressService.update(address);
    }
}
