package org.slzdevsnp.tmtrading;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slzdevsnp.binding.tmtrading.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


public class TestUnmarshal {

    @Test
    @Ignore
    void givenContractInfoReportUnmarshallPrint()  throws JAXBException {

        File file = new File("src/test/resources/ContractInfoRprt.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ContractInfoRprt.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ContractInfoRprt  rpt = (ContractInfoRprt) jaxbUnmarshaller.unmarshal(file);
        System.out.println(String.format("header provider: %s",rpt.getStandardHeader().getMarketId()));
        Assertions.assertTrue(rpt.getStandardHeader().getMarketId().equals("EPEX"));

        List<ContractListEntryType> contracts = rpt.getContractList().getContract();

        Assertions.assertTrue(contracts.size()>0);
        int cnt = 0;
        for (ContractListEntryType e : contracts){
            System.out.println(String.format("contractId:%d ; name:%s ; longname:%s ; state:%s",
                    e.getContractId() ,e.getName(),e.getLongName(),e.getState()));
            cnt+=1;
            List<DlvryAreaStateListEntryType>  deliveryAreaStates = e.getDlvryAreaState();
            for (DlvryAreaStateListEntryType  ds : deliveryAreaStates){
                System.out.println(String.format("\tareaId:%s ; state:%s ; tradPhase:%s",
                        ds.getDlvryAreaId(),ds.getState(),ds.getTradingPhase()));
            }
        }
        System.out.println("counted "+cnt+ " Contract msgs");
        Assertions.assertEquals(cnt, contracts.size());
    }

    @Test
    @Ignore
    void givenContractInfoReportUnmarshalPrintStream()  throws JAXBException {

        File file = new File("src/test/resources/ContractInfoRprt.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(ContractInfoRprt.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        ContractInfoRprt  rpt = (ContractInfoRprt) jaxbUnmarshaller.unmarshal(file);
        System.out.println(String.format("header provider: %s",rpt.getStandardHeader().getMarketId()));
        Assertions.assertTrue(rpt.getStandardHeader().getMarketId().equals("EPEX"));

        List<ContractListEntryType> contracts = rpt.getContractList().getContract();

        Assertions.assertTrue(contracts.size()>0);
        contracts.stream()
                .forEach(e -> {
                    System.out.println(String.format("contractId:%d ; name:%s ; longname:%s ; state:%s",
                            e.getContractId() ,e.getName(),e.getLongName(),e.getState()));
                    List<DlvryAreaStateListEntryType>  deliveryAreaStates = e.getDlvryAreaState();
                    deliveryAreaStates.stream()
                            .forEach( ds -> {
                                System.out.println(String.format("\tareaId:%s ; state:%s ; tradPhase:%s",
                                        ds.getDlvryAreaId(),ds.getState(),ds.getTradingPhase()));
                            });
                });

        Assertions.assertEquals(339, contracts.size());
    }

    @Test
    @Ignore
    void givenPublicOrderBookRespUnmarshall() throws JAXBException {
        File file = new File("src/test/resources/PblcOrdrBooksResp.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(PblcOrdrBooksResp.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        PblcOrdrBooksResp  resp = (PblcOrdrBooksResp) jaxbUnmarshaller.unmarshal(file);
        Assertions.assertTrue(resp.getStandardHeader().getMarketId().equals("EPEX"));
        List<OrdrBookType> ob = resp.getOrdrbookList().getOrdrBook();
        System.out.println("order book elements count: " + ob.size());
        Assertions.assertTrue(ob.size()>0);
        ob.stream()
                .forEach(o -> {
                    System.out.println(String.format("contract: %d dlvryArea: %s  revisionSeq %d",
                            o.getContractId(),o.getDlvryAreaId(),o.getRevisionNo()));
                     OrdrBookType.SellOrdrList sellOrders =  o.getSellOrdrList();
                     if (sellOrders != null){
                         //System.out.println("has some sell orders");
                         List<OrdrBookEntryType>  sellOrderEntries =  sellOrders.getOrdrBookEntry();
                         sellOrderEntries.stream().forEach(e -> {
                             System.out.println(String.format("\t sell ordId: %d  ; ord ts: %s  price: %d qty: %d",
                                     e.getOrdrId(),e.getOrdrEntryTime(),e.getPx(), e.getQty()));
                         });
                     }
                     OrdrBookType.BuyOrdrList buyOrders = o.getBuyOrdrList();
                     if (buyOrders!=null) {
                         //System.out.println("has some buy orders");
                         List<OrdrBookEntryType>  buyOrderEntries =  buyOrders.getOrdrBookEntry();
                         buyOrderEntries.stream().forEach(e ->{
                             System.out.println(String.format("\t buy ordId: %d  ; ord ts: %s  price: %d qty: %d",
                                     e.getOrdrId(),e.getOrdrEntryTime(),e.getPx(), e.getQty()));
                         });
                     }
                });
        System.out.println("processed " + ob.size() + " order book update messages");
    }

}


