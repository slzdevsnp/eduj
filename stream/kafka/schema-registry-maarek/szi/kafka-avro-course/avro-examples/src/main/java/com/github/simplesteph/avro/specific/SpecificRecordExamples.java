package com.github.simplesteph.avro.specific;

import com.example.AddressType;
import com.example.Customer;
import com.example.CustomerAddress;
import com.example.CustomerDomiciled;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SpecificRecordExamples {

    private static void writeReadCustomerDomiciled(){
        System.out.printf("#### writeReadCustomerDomiclied");
        CustomerDomiciled.Builder custDomiciledBuilder
                = CustomerDomiciled.newBuilder();
        custDomiciledBuilder.setAge(55);
        custDomiciledBuilder.setFirstName("Ivan");
        custDomiciledBuilder.setLastName("Petrov");
        custDomiciledBuilder.setAutomatedEmail(true);
        custDomiciledBuilder.setHeight(175f);
        custDomiciledBuilder.setWeight(90f);
        custDomiciledBuilder.setCustomerEmails(Arrays.asList("ip@abc.com", "ivan@gmail.com"));

        CustomerAddress.Builder custAdressBuilder = CustomerAddress.newBuilder();
        custAdressBuilder.setAddress("78th av 12 apt 3");
        custAdressBuilder.setCity("New-York");
        custAdressBuilder.setPostcode("US0723334");
        custAdressBuilder.setType(AddressType.RESIDENTIAL);

        custDomiciledBuilder.setCustomerAddress(custAdressBuilder
                                                .build());
        CustomerDomiciled customerDomi = custDomiciledBuilder.build();

        System.out.println("domiciled customer: " + customerDomi);

        //write to file
        final DatumWriter<CustomerDomiciled> datumWriter =
                new SpecificDatumWriter<>(CustomerDomiciled.class);

        try (DataFileWriter<CustomerDomiciled> dataFileWriter
                     = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customerDomi.getSchema(),
                        new File("customer-domiciled-specific.avro"));
            dataFileWriter.append(customerDomi);
            System.out.println("successfully wrote customer-domiciled-specific.avro");
        } catch (IOException e){
            e.printStackTrace();
        }
        //read from file
        final File file = new File("customer-domiciled-specific.avro");
        final DatumReader<CustomerDomiciled> datumReader
                = new SpecificDatumReader<>(CustomerDomiciled.class);
        final DataFileReader<CustomerDomiciled> dataFileReader;
        try {
            System.out.println("Reading our specific record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                CustomerDomiciled readCustomerDomi = dataFileReader.next();
                System.out.println("afer reading from file: "+ readCustomerDomi.toString());
                System.out.println("Address: " + readCustomerDomi.getCustomerAddress() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeReadCustomer(){
        System.out.printf("#### writeReadCustomer");
        // we can now build a customer in a "safe" way
        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(30);
        customerBuilder.setFirstName("Mark");
        customerBuilder.setLastName("Simpson");
        customerBuilder.setAutomatedEmail(true);
        customerBuilder.setHeight(175f);
        customerBuilder.setWeight(90f);

        Customer customer = customerBuilder.build();
        System.out.println("customer: " + customer.toString());


        // write it out to a file
        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);

        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-specific.avro"));
            dataFileWriter.append(customer);
            System.out.println("successfully wrote customer-specific.avro");
        } catch (IOException e){
            e.printStackTrace();
        }


        // read it from a file
        final File file = new File("customer-specific.avro");
        final DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        final DataFileReader<Customer> dataFileReader;
        try {
            System.out.println("Reading our specific record");
            dataFileReader = new DataFileReader<>(file, datumReader);
            while (dataFileReader.hasNext()) {
                Customer readCustomer = dataFileReader.next();
                System.out.println("afer reading from file: "+ readCustomer.toString());
                System.out.println("First name: " + readCustomer.getFirstName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // note, we can read our other customer generated using the generic method!
        // end of the day, no matter the method, Avro is Avro!
        final File fileGeneric = new File("customer-generic.avro");
        final DatumReader<Customer> datumReaderGeneric = new SpecificDatumReader<>(Customer.class);
        final DataFileReader<Customer> dataFileReaderGeneric;
        try {
            System.out.println("Reading our specific record");
            dataFileReaderGeneric = new DataFileReader<>(fileGeneric, datumReaderGeneric);
            while (dataFileReaderGeneric.hasNext()) {
                Customer readCustomer = dataFileReaderGeneric.next();
                System.out.println(readCustomer.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        writeReadCustomerDomiciled();
        writeReadCustomer();


    }
}

