package william.netty.thrift;

import org.apache.thrift.TException;
import william.netty.generated.CommonException;
import william.netty.generated.Person;
import william.netty.generated.PersonService;

/**
 * @Auther: ZhangShenao
 * @Date: 2019/1/20 17:55
 * @Description:Thrift接口的实现类
 */
public class PersonServiceHandler implements PersonService.Iface{
    @Override
    public Person getPersonByName(String name) throws CommonException, TException {
        System.err.println("Get Person By Name,name: " + name);
        Person person = new Person();
        person.setName("James");
        person.setAge(34);
        person.setMarried(true);
        return person;
    }

    @Override
    public void savePerson(Person person) throws CommonException, TException {
        System.err.println("Save Person: " + person);

    }
}
