package sample;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class checkOutput {

//    public String estTimeArrive() {
//        final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";
//
//        String leaveDateTime = departDate + " " + departTime;
//        LocalDateTime ldt = LocalDateTime.parse(leaveDateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));
//
//        double earthRadius = 6371.01 * 0.621;
//
//        distance = Math.round(earthRadius * Math.acos(Math.sin(depart.getLat()) * Math.sin(arrive.getLat())
//                + Math.cos(depart.getLat()) * Math.cos(arrive.getLat()) * Math.cos(depart.getLon() - arrive.getLon())));
//
//        double distance1 = distance / 50;
//        hours = Math.floor(distance1);
//        minutes = Math.ceil((distance1 - hours) * 60);
//
//        LocalDateTime ldt2 = ldt.plusHours((long) hours).plusMinutes((long) minutes);
//
//
//        ZoneId fromId = ZoneId.of(depart.getTimeZoneString());
//        ZoneId toId = ZoneId.of(arrive.getTimeZoneString());
//
//        ZonedDateTime currentTime = ldt2.atZone(fromId);
//
//        ZonedDateTime newTime = currentTime.withZoneSameInstant(toId);
//
//        //condition to add day
//        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern(DATE_FORMAT);
//        arriveTime = dateFormat2.format(newTime);
//        System.out.println(arriveTime);
//        return arriveTime;
//
//    }
}
