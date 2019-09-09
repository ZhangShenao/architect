//命名空间定义，即Java的包
namespace java william.netty.generated

//类型别名定义
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

//结构体定义
struct Person{
    1: optional String name;
    2: optional int age;
    3: optional boolean married;
}

//异常定义
exception CommonException{
    1: optional String message;
    2: optional String callStack;
    3: optional long timestamp;
}

//接口定义
service PersonService{
    Person getPersonByName(1: required String name) throws (1: CommonException e);
    void savePerson(1: required Person person) throws (1: CommonException e);
}

