package com.elizabeth.batch;

import com.elizabeth.entity.User;
import org.beanio.BeanReader;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Map;

public class UserReader implements ItemReader<User> {

    public UserReader(final @Value("input.file") Resource resource) {
        super();
//        setResource
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] {"userId", "firstName", "accountBalance"});
    }

    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }


    public static void main(String[] args) throws Exception {
        // create a StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file
        factory.load("src/main/resources/users.xml");

        // use a StreamFactory to create a BeanReader
//        BeanReader in = factory.createReader("user", new File("users.csv"));
        BeanReader in = factory.createReader("users", new File(UserReader.class.getClassLoader().getResource("users.csv").getFile()));
        BeanWriter out = factory.createWriter("users", new File("src/main/resources/output.csv"));

        Object record = null;

        while ((record = in.read()) != null) {
            if("header".equals(in.getRecordName())) {
                Map<String, Object> header = (Map<String, Object>) record;
                System.out.println(header.get("fileDate"));
            } else if("user".equals(in.getRecordName())){
                User user = (User) record;
                System.out.println(user.getUserId() + " " + user.getFirstName() + " " + user.getAccountBalance());
            } else if("trailer".equals(in.getRecordName())){
                Integer recordCount = (Integer) record;
                System.out.println(recordCount + " users processed");
            }

            out.write(record);
            // process the user...
        }
        in.close();

        out.flush();
        out.close();
    }
}
