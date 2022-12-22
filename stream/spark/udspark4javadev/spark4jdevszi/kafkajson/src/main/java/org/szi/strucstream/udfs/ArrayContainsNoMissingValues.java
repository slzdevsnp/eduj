package org.szi.strucstream.udfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.api.java.UDF1;
import scala.collection.Iterator;
import scala.collection.mutable.WrappedArray;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class ArrayContainsNoMissingValues implements UDF1<WrappedArray, Boolean> {
    @Override
    public Boolean call(WrappedArray numValues)  {
        log.debug("Array - {}", numValues);
        Set<Integer> tdiffs = new HashSet<>();
        Double prevTs = null;
        Iterator it = numValues.iterator();
        while (it.hasNext()) {
            WrappedArray<Double> el = (WrappedArray<Double>) it.next();
            log.debug(el.apply(0) + " : " + el.apply(1));
            if (prevTs != null) {
                Double tdiff = new Double(el.apply(0).doubleValue()-prevTs.doubleValue());
                tdiffs.add(Integer.valueOf(tdiff.intValue()));
                log.debug("diff: " + tdiff.intValue());
            }
            prevTs = el.apply(0);
        }
        log.debug("tdiffs - {}", tdiffs);
        if ( tdiffs.size() > 1 ){ return Boolean.FALSE; }
        return Boolean.TRUE;
    }
}
