package com.jiao.testproject.testproject;
import cn.hutool.core.lang.func.VoidFunc0;
import com.jiao.testproject.testproject.dao.impl.CustomerCrudRepository;
import com.jiao.testproject.testproject.dto.FileViewVo;
import com.jiao.testproject.testproject.dto.FolderDto;
import com.jiao.testproject.testproject.dto.Node;
import com.jiao.testproject.testproject.entity.FileEntity;
import com.jiao.testproject.testproject.test.CasTest;
import com.jiao.testproject.testproject.test.LambdaQs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;


@SpringBootTest
class TestProjectApplicationTests {
    @Resource
    CustomerCrudRepository customerCrudRepository;

    @Test
    void contextLoads() throws InstantiationException, IllegalAccessException {

        /*      FileDto fileDto = new FileDto();
        FileEntity fileEntity = new FileEntity();
        fileDto.setFileid(1);
        fileDto.setFileName("file");
        fileDto.setFileSize("123");
        this.reflectField(fileDto,fileEntity);
        System.out.println(fileEntity.getFile_id());
        System.out.println(fileEntity.getFile_name());
        System.out.println(fileEntity.getFile_size());*/
        //String s = excludeString("E://file_disk//import/profile/import/2022/04/17");
        //System.out.println(getSysTime());
        //System.out.println(s);
        //openFile();
        //testStream();
        //yunsuan();
/*        Integer i=11;
        Integer i1=new Integer(11);
        Integer i2=new Integer(200);
        System.out.println(i == i1);
        System.out.println(i2 == i1);
        System.out.println(i2 == 11);*/
//        Integer integer = new Integer(100000);
//        System.out.println(i2 == 100000);
//        Integer i2= 100000;
//        System.out.println(integer == i2);
/*
        FolderDto folderDto = new FolderDto();
        FolderDto folderDto1 = new FolderDto();
        FolderDto folderDto2 = new FolderDto();
        FolderDto folderDto3 = new FolderDto();
        FolderDto folderDto4 = new FolderDto();
        FolderDto folderDto5 = new FolderDto();
        FolderDto folderDto6 = new FolderDto();*/
/*
        String x="0";
        File file = new File("E:\\VirtaulDisk\\zs_2-VirtaulDisk");
        calculateFolderLength(file,x);
        System.out.println(x);
        System.out.println(file.length());
*/
//        FileViewVo fileViewVo = new FileViewVo();
//        fileViewVo.setName("jiao");
//        this.setName(fileViewVo);
//        System.out.println(fileViewVo.getName());

//        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
//        ArrayList<Node> nodes = new ArrayList<>();
//        AtomicInteger i= new AtomicInteger(1);
//        Node nodeGen = new Node();
//        nodeGen.setNodeNo(String.valueOf(i.getAndIncrement()));
//        nodeGen.setParentNodeNo(null);
//        nodes.add(nodeGen);
//        for (int j = 0; j < 10; j++) {
//            Node node = new Node();
//            node.setNodeNo(String.valueOf(i.incrementAndGet()));
//            i.getAndDecrement();
//            node.setParentNodeNo(String.valueOf(i.getAndDecrement()));
//            nodes.add(node);
//        }
//        List<Node> nodes1 = listToTree(nodes);
//        HashSet<Object> objects = new HashSet<>();

          //testJPACrud();
  /*      CasTest.testCas();*/
//        String a = "1";
//        a = "2";
//        String b = "1";
//        Integer c = 1;
//        Integer d = 1;
//        int e =1;
//        int f= 1;
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
//        System.out.println(a.equals(b));
//        System.out.println(c.hashCode());
//        System.out.println(d.hashCode());

        /*testJdk8();*/
//
//        Cal c=(int a,int b)->{
//            return a+b;
//        };
//        System.out.println(c.add(1,2));


//        Integer[] arr = {1,null,2,3,null};
//        List<Integer> integers = Arrays.asList(arr);
//        List<Integer> collect = integers.parallelStream().filter(Objects::nonNull).collect(Collectors.toList());
//        /*collect.parallelStream().forEach(out::println);*/

       // System.out.println(false || false);

//        List<String> strings = new ArrayList<>();
//        System.out.println(strings.size()+"ready go");
//        if (strings == null || strings.isEmpty()){
//            System.out.println("?????????"+strings.size());
//        }else {
//            System.out.println("go ");
//        }

        calculatorNumber();

    }


    void reflectField(Object source, Object target) throws InstantiationException, IllegalAccessException {
        Class<?> class_s = source.getClass();
        java.lang.Class<?> class_t = target.getClass();
        Field[] field_s = class_s.getDeclaredFields();
        Field[] field_t = class_t.getDeclaredFields();
        for (int i = 0; i <= field_s.length; i++) {
            field_s[i].setAccessible(true);
            field_t[i].setAccessible(true);
            System.out.println("??????????????????" + field_s[i].get(source));
            field_t[i].set(target, field_s[i].get(source));
        }
    }

    public static String getSysTime() {
        Date now = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd:HH:mm");
        return f.format(now);

    }

    public static String excludeString(String s){
        String rexg="profile/import/";
        String[] split = s.split(rexg);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(split[0]);
        stringBuilder.append(split[1]);
        String s1 = stringBuilder.toString();
        return s1;
    }

    public void openFile() {
        try {
           String  filePath="E:"+File.separator+"a.txt";
            File file = new File(filePath); // ??????????????????????????????filePath
            try {
                Desktop.getDesktop().open(file); // ???????????????????????????????????????????????????????????????????????????file???
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) { // ????????????
            System.err.println(e);
        }
    }

    public void testStream(){
        //List<String> list = Arrays.asList("a", "b", "c");
        // ?????????????????????
        //Stream<String> stream = list.stream();
        // ?????????????????????
        //Stream<String> parallelStream = list.parallelStream();

/*        int[] array={1,3,5,6,8};
        IntStream stream1 = Arrays.stream(array);
        stream1.forEach(System.out::print);*/

        //List<Integer> list = Arrays.asList(7, 6, 9, 3, 8, 2, 1);
   /*       // ?????????????????????????????????
        list.stream().filter(x -> x > 6).forEach(System.out::println);*/

        //Optional<Integer> findAny = list.parallelStream().filter(x -> x > 6).findAny();

        //boolean anyMatch = list.stream().anyMatch(x -> x > 6);
        //System.out.println("?????????????????????" + findFirst.get());
        //System.out.println("????????????????????????" + findAny.get());
        //System.out.println("??????????????????6?????????" + anyMatch);
/*        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200, "female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900, "female", "New York"));
        List<String> fiterList = personList.stream().filter(x -> x.getSalary() > 8000).map(Person::getName)
                .collect(Collectors.toList());
        System.out.print("??????8000??????????????????" + fiterList);*/
/*
        List<String> list = Arrays.asList("adnm", "admmt", "pot", "xbangd", "weoujgsd");
        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("?????????????????????" + max.get());*/

        //List<Integer> list = Arrays.asList(7, 6, 9, 4, 11, 6);

/*        // ????????????
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        // ???????????????
        Optional<Integer> max2 = list.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println("???????????????????????????" + max.get());
        System.out.println("??????????????????????????????" + max2.get());*/
  /*      List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, "male", "New York"));
        personList.add(new Person("Jack", 7000, "male", "Washington"));
        personList.add(new Person("Lily", 7800, "female", "Washington"));
        personList.add(new Person("Anni", 8200,"female", "New York"));
        personList.add(new Person("Owen", 9500, "male", "New York"));
        personList.add(new Person("Alisa", 7900,  "female", "New York"));

        Optional<Person> max = personList.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("????????????????????????" + max.get().getSalary());*/
/*

        List<Integer> list = Arrays.asList(1, 3, 2, 8, 11, 4);
        // ????????????1
        Optional<Integer> sum = list.stream().reduce((x, y) -> x + y);
        // ????????????2
        Optional<Integer> sum2 = list.stream().reduce(Integer::sum);
        // ????????????3
        Integer sum3 = list.stream().reduce(0, Integer::sum);

        // ?????????
        Optional<Integer> product = list.stream().reduce((x, y) -> x * y);

        // ??????????????????1
        Optional<Integer> max = list.stream().reduce((x, y) -> x > y ? x : y);
        // ??????????????????2
        Integer max2 = list.stream().reduce(1, Integer::max);

        System.out.println("list?????????" + sum.get() + "," + sum2.get() + "," + sum3);
        System.out.println("list?????????" + product.get());
        System.out.println("list?????????" + max.get() + "," + max2);
*/
//        Cal c=(int a,int b)->{
//            return a+b;
//        };
//
//        System.out.println(c.add(1,2));
    }

    void yunsuan(){
        String s = Integer.toBinaryString(1024);
        int length = s.length();
        System.out.println(s);
        System.out.println(length);
    }

    public static List<FileViewVo> IteratorCollectAndAddId(List<FolderDto> list){

        list.stream().map(x->x.getParent_name()).distinct().collect(Collectors.toList()).forEach(x ->{
            list.stream().filter(y->{
                if(x.equals(y.getParent_name())) {
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        });
        return  null;
    }

    private <T> void calculateFolderLength(T folder,String x){

        if (folder instanceof  File){
            File[] files = ((File) folder).listFiles();
            for (File f: files) {
                if (f.isFile()){
                    x += f.length();
                    System.out.println(x);
                }
                if (f.isDirectory()){
                    calculateFolderLength(f,x);
                }
            }
        }
    }

    void setName(FileViewVo fvv){
        FileViewVo s =fvv;
        s.setName("rong");
        System.out.println(fvv.getName());
    }

             //????????????sourceList  ???????????????ChildNodes??????????????? ????????????????????????
    List<Node> listToTree(List <Node> sourceList){
                 //1.????????????????????? ?????????????????????(s)  ????????????Map????????????????????????????????????

                 List<Node> rootNodes= new ArrayList<Node>();
                 Map<String, Set<Node>> childNodesMap= new HashMap<String, Set<Node>>();

                 //2.?????????????????????
                 for (Node node: sourceList) {
                         //3.??????Map??????????????????Node?????????????????? ???????????????
                         if (childNodesMap.get(node.getNodeNo()) == null) {
                                 childNodesMap.put(node.getNodeNo(), new HashSet<Node >());
                             }
                         //4.???childNodes????????????Node
                         node.setChildNodes(childNodesMap.get(node.getNodeNo()));
                         //5.?????????Node??????Node????????????????????????
                         if (node.getParentNodeNo() == null) {
                                 rootNodes.add(node);
                             }
                         if (node.getParentNodeNo() != null) {
                                  //6.?????????Node???ParentNode??? ChildNodes???????????? ???????????????
                                 if (childNodesMap.get(node.getParentNodeNo()) == null) {
                                        childNodesMap.put(node.getParentNodeNo(), new HashSet<Node >());
                                    }
                                 //7.???????????? ?????????ParentNodes???ChildNodes???
                                 childNodesMap.get(node.getParentNodeNo()).add(node);
                             }
                     }
                 return rootNodes;
         }

    // ??????crud
    private void testJPACrud(){

        String sql="SELECT *  FROM filelist";
        List list = customerCrudRepository.sqlObjectList(sql, null, FileEntity.class);
        list.stream().forEach(x->{
            System.out.println(x);
        });
    }

    void testJdk8(){
        Runnable runnable=() -> System.out.print("Hello Lambda");
        Runnable runnable_1=() -> System.out.print("Hello Lambda");
        Runnable runnable_2=() -> System.out.print("Hello Lambda");


    }

    BinaryOperator<Integer> bo=(x, y)->{
        System.out.println("????????????????????????????????????");
        return x+y;
    };

    interface Cal{
        int add(int a,int b);
        int div(int a,int b);
    }

    void calculatorNumber(){
        long a = 10L;
        double b = 2.5;
        float c = 3.0f;
        double v = a * c;
        double v1 = a / b / c;
        //===============
        double v4 = a * b;
        float v2 = a * c;
        double v3 = b * c;

        double e=3.14;
        float ef =(float) e;

        float g = 3.14f ;
        double gh = (double) g ;
        BigDecimal bigDecimal = BigDecimal.valueOf(g);
        System.out.println(bigDecimal.floatValue());
        System.out.println(gh);
        System.out.println(ef);
        System.out.println(v1);
        System.out.println(v);
    }




}