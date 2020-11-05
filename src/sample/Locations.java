package sample;

public class Locations {

        private int id;
        private String city;
        private String country;
        private String state;
        private double lat;
        private double lon;

        public Locations(int id, String country, String city, double lat, double lon) {
            this.id = id;
            this.country = country;
            this.state = "";
            this.city = city;
            this.lat = lat;
            this.lon = lon;
        }
        public Locations(int id, String country, String state, String city, double lat, double lon) {
            this.id = id;
            this.country = country;
            this.state = state;
            this.city = city;
            this.lat = lat;
            this.lon = lon;
        }

        public Locations() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getCountry() {
            return country;
        }

        public String getTimeZoneString(){
            if(!state.equals(""))
                return country+"/"+state+"/"+city; // America/Indiana/Indianapolis;
            return country+"/"+city; //America/New_york
        }




    }

