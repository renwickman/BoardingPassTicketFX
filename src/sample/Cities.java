package sample;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cities {

    public HashMap<String, Coordinates> latLon = new HashMap<>();

    public Cities(){
        latLon.put("New_York", new Coordinates(40.7648, -73.9808));
        latLon.put("Los_Angeles", new Coordinates(34.05223, -118.24368));
        latLon.put("Detroit", new Coordinates(42.331427, -83.045754));
        latLon.put("Phoenix", new Coordinates(33.448377, -112.074037));
        latLon.put("Louisville", new Coordinates(38.252665, -85.758456));
        latLon.put("Indianapolis", new Coordinates(39.76863, -86.15804));
    }

    public HashMap<String, Coordinates> getCityList() {

        return latLon;
    }


//    @Override
//    public String toString(){
//        StringBuilder sb = new StringBuilder();
//        for (Map.Entry<String, Coordinates> e: latLon
//        ) {
//            sb.append(location.getId() + "." +location.getTimeZoneString() + "" +
//                    "\n");
//        }
//        return sb.toString();
//    }
}
