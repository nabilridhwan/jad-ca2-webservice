/*
 * 	Name: Xavier Tay Cher Yew, Nabil Ridhwanshah Bin Rosli
	Admin No: P2129512, P2007421
	Class: DIT/FT/2A/01
	Group Number: Group 4 - TAY CHER YEW XAVIER, NABIL RIDHWANSHAH BIN ROSLI  
 * */

package dataStructures;

import DAO.TourModel;
import dataStructures.Tour.Registrations;
import utils.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tour {
    int tour_id;
    String tour_name;
    String tour_brief_desc;
    String tour_desc;
    String tour_location;
    Image[] images;
    Date[] dates;
    Registrations[] registrations;
    double average_rating;
    Review[] reviews;
    boolean refreshed;

    public Tour(ResultSet rs) {
        try {
            tour_id = rs.getInt("tour_id");
            tour_name = rs.getString("name");
            tour_brief_desc = rs.getString("brief_desc");
            tour_desc = rs.getString("detail_desc");
            tour_location = rs.getString("location");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tour() {
        tour_id = 0;
        tour_name = "";
        tour_brief_desc = "";
        tour_desc = "";
        tour_location = "";
        images = new Image[0];
        dates = new Date[0];
        average_rating = 0;
        reviews = new Review[0];
    }

    public int getTour_id() {
        return tour_id;
    }

    public String getTour_name() {
        return tour_name;
    }

    public String getTour_brief_desc() {
        return tour_brief_desc;
    }

    public String getTour_desc() {
        return tour_desc;
    }

    public String getTour_location() {
        return tour_location;
    }


    public Date[] getDates() {
        if (!refreshed) refreshTour();
        return dates;
    }

    public Image[] getImages() {
        if (!refreshed) refreshTour();
        return images;
    }

    public Image getFirstOrDefaultImage() {
        if (!refreshed) refreshTour();
        if ( images.length > 0) {
            return images[0];
        }
        return new Image();
    }

    public Tour.Date getFirstOrDefaultDate() {
        if (!refreshed) refreshTour();
        if (dates.length > 0) {
            return dates[0];
        }
        return new Tour.Date();
    }

    public double getAverage_rating() {
        if (!refreshed) refreshTour();
        return average_rating;
    }

    public Review[] getReviews() {
        if (!refreshed) refreshTour();
        return reviews;
    }

    public void refreshTour(DatabaseConnection conn) {
        dates = TourModel.getTourDates(this).query(conn);
        
        // Get registrations
        for(Date date : dates) {
        	Registrations[] registrations = TourModel.getTourRegistrationByTourDateId(date).query(conn);
        	date.setRegistrations(registrations);
        }
        
        images = TourModel.getTourImages(this).query(conn);
        reviews = TourModel.getTourReviews(this).query(conn);
        average_rating = TourModel.getTourReviewAverage(this).query(conn)[0];
        refreshed = true;
    }

    public void refreshTour() {
        DatabaseConnection DB = new DatabaseConnection();
        refreshTour(DB);
        DB.close();
    }

    public static class Image {
        public int getId() {
            return id;
        }

        int id;
        String altText;
        String url;

        public Image(ResultSet rs) {
            try {
                id = rs.getInt("tour_image_id");
                altText = rs.getString("alt_text");
                url = rs.getString("url");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Image() {
            altText = "";
            url = "";
        }

        public String getAltText() {
            return altText;
        }

        public String getUrl() {
            return url;
        }
    }

    public static class Date {
        int id;

        public int getId() {
            return id;
        }

        public java.util.Date start;
        public java.util.Date end;
        boolean shown;
        double price;
        int avail_slot;
        int max_slot;
        
        private Registrations[] registrations;

        public Registrations[] getRegistrations() {
			return registrations;
		}

		public void setRegistrations(Registrations[] registrations) {
			this.registrations = registrations;
		}

		public Date(ResultSet rs) {
        	DatabaseConnection conn = new DatabaseConnection();
            try {
                id = rs.getInt("tour_date_id");
                start = new java.util.Date(rs.getTimestamp("tour_start").getTime());
                end = new java.util.Date(rs.getTimestamp("tour_end").getTime());
                shown = rs.getByte("show_tour") == 1;
                price = rs.getDouble("price");
                avail_slot = rs.getInt("avail_slot");
                max_slot = rs.getInt("max_slot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Date() {
            id = 0;
            start = new Timestamp(Calendar.getInstance().getTime().getTime());
            end = new Timestamp(Calendar.getInstance().getTime().getTime());
            shown = false;
            price = 0;
            avail_slot = 0;
            max_slot = 0;
        }

        public java.util.Date getStart() {
            return start;
        }

        public String getStartString() {
            return start.toString();
        }

        public java.util.Date getEnd() {
            return end;
        }

        public String getEndString() {
            return end.toString();
        }

        private static String ParseTimeStamp(Timestamp timestamp) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(timestamp);
            //2 digit
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault());
            int year = calendar.get(Calendar.YEAR);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            return String.format("%02d", day) + " " + month + " " + year + " " + String.format("%02d", hour) + ":" + String.format("%02d", minute);
        }

        public boolean isShown() {
            System.out.println(shown);
            return shown;
        }

        public double getPrice() {
            return price;
        }

        public int getAvail_slot() {
            return avail_slot;
        }

        public int getMax_slot() {
            return max_slot;
        }

        public String getDuration() {
            // add 1 day to include the start day
            return String.format("%02d", (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)) + 1) + " days";
        }

        @Override
        public String toString() {
            return getStartString() + "-" + getEndString() + " (" + getDuration() + ")";
        }
    }

    public static class Review {
        int user_id;
        String review_text;
        int rating;
        int helpful_score;

        public Review(ResultSet rs) {
            try {
                user_id = rs.getInt("user_id");
                review_text = rs.getString("text");
                rating = rs.getInt("rating");
                helpful_score = rs.getInt("score");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public int getUser_id() {
            return user_id;
        }

        public String getReview_text() {
            return review_text;
        }

        public int getRating() {
            return rating;
        }

        public int getHelpful_score() {
            return helpful_score;
        }
    }

    public static class Registrations {
        public int getId() {
            return id;
        }

        public int getUser_id() {
            return user_id;
        }

        public int getTour_date_id() {
            return tour_date_id;
        }

        public byte getPax() {
            return pax;
        }
        
        public java.util.Date getCreatedAt(){
        	return created_at;
        }

        private int id;
        private int user_id;
        private int tour_date_id;
        private byte pax;
        private java.util.Date created_at;

        public Registrations(ResultSet rs) {
            try {
                id = rs.getInt("tour_registration_id");
                user_id = rs.getInt("user_id");
                tour_date_id = rs.getInt("tour_date_id");
                pax = rs.getByte("pax");
                created_at = new java.util.Date(rs.getTimestamp("created_at").getTime());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
